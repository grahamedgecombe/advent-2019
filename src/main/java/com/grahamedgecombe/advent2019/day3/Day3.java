package com.grahamedgecombe.advent2019.day3;

import java.util.List;
import java.util.stream.Collectors;

import com.grahamedgecombe.advent2019.Day;

public final class Day3 extends Day<List<Wire>> {
	public Day3() {
		super(3);
	}

	@Override
	public List<Wire> parse(List<String> lines) {
		return lines.stream()
			.map(Wire::parse)
			.collect(Collectors.toList());
	}

	@Override
	public Object solvePart1(List<Wire> input) {
		return Grid.trace(input).getDistanceToClosestIntersection().orElseThrow();
	}
}
