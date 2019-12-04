package com.grahamedgecombe.advent2019.day4;

public final class Range {
	public static Range parse(String line) {
		var parts = line.split("-");
		var min = Integer.parseInt(parts[0]);
		var max = Integer.parseInt(parts[1]);
		return new Range(min, max);
	}

	private final int min, max;

	public Range(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
}
