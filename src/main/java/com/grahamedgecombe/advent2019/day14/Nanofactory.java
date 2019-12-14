package com.grahamedgecombe.advent2019.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public final class Nanofactory {
	public static Nanofactory parse(List<String> lines) {
		var builder = ImmutableMap.<String, Reaction>builder();

		for (var line : lines) {
			var reaction = Reaction.parse(line);
			builder.put(reaction.getOutput().getName(), reaction);
		}

		return new Nanofactory(builder.build());
	}

	private final ImmutableMap<String, Reaction> reactions;

	public Nanofactory(ImmutableMap<String, Reaction> reactions) {
		this.reactions = reactions;
	}

	private int getRequiredOre(String chemical, int quantity, Map<String, Integer> spares) {
		/* base case */
		if (chemical.equals("ORE")) {
			return quantity;
		}

		/* try to use as much spare chemical as possible */
		var spareQuantity = spares.getOrDefault(chemical, 0);
		if (spareQuantity == quantity) {
			spares.remove(chemical);
			return 0;
		} else if (spareQuantity > quantity) {
			spares.put(chemical, spareQuantity - quantity);
			return 0;
		}

		quantity -= spareQuantity;
		spares.remove(chemical);

		/* produce more via reaction */
		var reaction = reactions.get(chemical);
		var outputQuantity = reaction.getOutput().getQuantity();
		var n = (quantity + outputQuantity - 1) / outputQuantity; /* number of times to run the reaction rounded up */

		/* sum required ore for each input */
		var requiredOre = 0;
		for (var input : reaction.getInputs()) {
			requiredOre += getRequiredOre(input.getName(), input.getQuantity() * n, spares);
		}

		/* add spare output chemical */
		spareQuantity = (outputQuantity * n) - quantity;
		if (spareQuantity > 0) {
			spares.merge(chemical, spareQuantity, Integer::sum);
		}

		return requiredOre;
	}

	public int getRequiredOre() {
		return getRequiredOre("FUEL", 1, new HashMap<>());
	}
}
