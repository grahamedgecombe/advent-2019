package com.grahamedgecombe.advent2019.day22;

import java.util.List;
import java.util.stream.Collectors;

public interface Technique {
	static Technique parse(String line) {
		final var cut = "cut ";
		final var dealWithIncrement = "deal with increment ";

		if (line.equals("deal into new stack")) {
			return DealIntoNewStack.INSTANCE;
		} else if (line.startsWith(cut)) {
			var n = Integer.parseInt(line.substring(cut.length()));
			return new Cut(n);
		} else if (line.startsWith(dealWithIncrement)) {
			var n = Integer.parseInt(line.substring(dealWithIncrement.length()));
			return new DealWithIncrement(n);
		}

		throw new IllegalArgumentException();
	}

	static List<Technique> parseAll(List<String> lines) {
		return lines.stream()
			.map(Technique::parse)
			.collect(Collectors.toList());
	}

	static List<Integer> runAll(List<Integer> deck, List<Technique> techniques) {
		for (var technique : techniques) {
			deck = technique.run(deck);
		}
		return deck;
	}

	List<Integer> run(List<Integer> cards);
}
