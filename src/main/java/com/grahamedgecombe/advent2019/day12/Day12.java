package com.grahamedgecombe.advent2019.day12;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;

public final class Day12 extends Day<Planet> {
	public Day12() {
		super(12);
	}

	@Override
	public Planet parse(List<String> lines) {
		return Planet.parse(lines);
	}

	@Override
	public Object solvePart1(Planet input) {
		input.tick(1000);
		return input.getEnergy();
	}
}
