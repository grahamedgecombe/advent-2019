package com.grahamedgecombe.advent2019.day24;

import java.util.HashSet;
import java.util.List;

import com.grahamedgecombe.advent2019.Day;

public final class Day24 extends Day<Grid> {
	public static Grid getFirstRepeated(Grid grid) {
		var history = new HashSet<Grid>();
		while (history.add(grid)) {
			grid = grid.next();
		}
		return grid;
	}

	public Day24() {
		super(24);
	}

	@Override
	public Grid parse(List<String> lines) {
		return Grid.parse(lines);
	}

	@Override
	public Object solvePart1(Grid input) {
		return getFirstRepeated(input).getBiodiversity();
	}
}
