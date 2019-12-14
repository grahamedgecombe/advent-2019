package com.grahamedgecombe.advent2019.day14;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public final class Reaction {
	public static Reaction parse(String line) {
		var parts = line.split(" => ");
		Preconditions.checkArgument(parts.length == 2);

		var builder = ImmutableList.<Chemical>builder();
		for (var input : parts[0].split(", ")) {
			builder.add(Chemical.parse(input));
		}

		var output = Chemical.parse(parts[1]);

		return new Reaction(builder.build(), output);
	}

	private final ImmutableList<Chemical> inputs;
	private final Chemical output;

	public Reaction(ImmutableList<Chemical> inputs, Chemical output) {
		this.inputs = inputs;
		this.output = output;
	}

	public ImmutableList<Chemical> getInputs() {
		return inputs;
	}

	public Chemical getOutput() {
		return output;
	}

	@Override
	public String toString() {
		return Joiner.on(", ").join(inputs) + " => " + output;
	}
}
