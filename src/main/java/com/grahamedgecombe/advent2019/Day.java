package com.grahamedgecombe.advent2019;

import java.util.List;

public abstract class Day<T> {
	private final int number;

	public Day(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public abstract T parse(List<String> lines);
	public abstract Object solvePart1(T input);

	public Object solvePart2(T input) {
		return null;
	}
}
