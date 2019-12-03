package com.grahamedgecombe.advent2019.day3;

import java.util.List;
import java.util.stream.Collectors;

import com.grahamedgecombe.advent2019.Day;

public final class Day3 extends Day<Grid> {
	public Day3() {
		super(3);
	}

	@Override
	public Grid parse(List<String> lines) {
		return Grid.trace(lines.stream()
			.map(Wire::parse)
			.collect(Collectors.toList()));
	}

	@Override
	public Object solvePart1(Grid input) {
		return input.getDistanceToClosestIntersection().orElseThrow();
	}

	@Override
	public Object solvePart2(Grid input) {
		return input.getSmallestDelayIntersection().orElseThrow();
	}
}
