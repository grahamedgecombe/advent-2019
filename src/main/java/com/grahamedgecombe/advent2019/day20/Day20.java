package com.grahamedgecombe.advent2019.day20;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;

public final class Day20 extends Day<Grid> {
	public Day20() {
		super(20);
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
