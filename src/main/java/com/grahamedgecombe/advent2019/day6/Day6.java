package com.grahamedgecombe.advent2019.day6;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;

public final class Day6 extends Day<Universe> {
	public Day6() {
		super(6);
	}

	@Override
	public Universe parse(List<String> lines) {
		return Universe.parse(lines);
	}

	@Override
	public Object solvePart1(Universe input) {
		return input.getOrbits();
	}

	@Override
	public Object solvePart2(Universe input) {
		return input.getTransfers();
	}
}
