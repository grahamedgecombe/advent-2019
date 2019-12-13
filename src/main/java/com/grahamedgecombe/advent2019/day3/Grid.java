package com.grahamedgecombe.advent2019.day3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

import com.google.common.base.Preconditions;
import com.grahamedgecombe.advent2019.Vector2;

public final class Grid {
	public static Grid trace(List<Wire> wires) {
		Preconditions.checkArgument(wires.size() == 2);

		var grid = new Grid();
		for (var wire : wires) {
			grid.trace(wire);
		}
		return grid;
	}

	private final Map<Vector2, Cell> cells = new HashMap<>();

	private Grid() {
		/* empty */
	}

	private void trace(Wire wire) {
		var position = Vector2.ORIGIN;
		int delay = 0;

		for (var step : wire.getSteps()) {
			var direction = step.getDirection();

			for (int i = 0; i < step.getCount(); i++) {
				position = position.add(direction);

				var cell = cells.computeIfAbsent(position, p -> new Cell());
				cell.trace(wire, ++delay);
			}
		}
	}

	public OptionalInt getDistanceToClosestIntersection() {
		return cells.entrySet().stream()
			.filter(e -> e.getValue().isIntersection())
			.map(Map.Entry::getKey)
			.mapToInt(Vector2.ORIGIN::getManhattanDistance)
			.min();
	}

	public OptionalInt getSmallestDelayIntersection() {
		return cells.values().stream()
			.filter(Cell::isIntersection)
			.mapToInt(Cell::getSignalDelay)
			.min();
	}
}
