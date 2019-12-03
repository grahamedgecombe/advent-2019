package com.grahamedgecombe.advent2019.day3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Wire {
	public static Wire parse(String line) {
		return new Wire(Arrays.stream(line.split(","))
			.map(Step::parse)
			.collect(Collectors.toUnmodifiableList()));
	}

	private final List<Step> steps;

	public Wire(List<Step> steps) {
		this.steps = steps;
	}

	public List<Step> getSteps() {
		return steps;
	}

	@Override
	public String toString() {
		return steps.toString();
	}
}
