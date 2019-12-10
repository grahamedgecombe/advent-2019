package com.grahamedgecombe.advent2019.day10;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;

public final class Day10 extends Day<Grid> {
	public Day10() {
		super(10);
	}

	@Override
	public Grid parse(List<String> lines) {
		return Grid.parse(lines);
	}

	@Override
	public Object solvePart1(Grid input) {
		return input.getMaxVisibleAsteroids();
	}

	@Override
	public Object solvePart2(Grid input) {
		var position = input.vaporizeAsteroids().orElseThrow();
		return position.getX() * 100 + position.getY();
	}
}
