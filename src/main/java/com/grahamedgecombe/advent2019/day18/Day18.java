package com.grahamedgecombe.advent2019.day18;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;

public final class Day18 extends Day<Grid> {
	public Day18() {
		super(18);
	}

	@Override
	public Grid parse(List<String> lines) {
		return Grid.parse(lines);
	}

	@Override
	public Object solvePart1(Grid input) {
		return input.getSteps();
	}
}
