package com.grahamedgecombe.advent2019.day3;

import java.util.HashMap;
import java.util.Map;

public final class Cell {
	private final Map<Wire, Integer> visitedWires = new HashMap<>();

	public void trace(Wire wire, int delay) {
		visitedWires.putIfAbsent(wire, delay);
	}

	public boolean isIntersection() {
		return visitedWires.size() == 2;
	}

	public int getSignalDelay() {
		return visitedWires.values().stream()
			.mapToInt(i -> i)
			.sum();
	}
}
