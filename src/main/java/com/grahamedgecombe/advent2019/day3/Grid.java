package com.grahamedgecombe.advent2019.day3;

import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.grahamedgecombe.advent2019.Position;

public final class Grid {
	public static Grid trace(List<Wire> wires) {
		Preconditions.checkArgument(wires.size() == 2);

		var grid = new Grid();
		for (var wire : wires) {
			grid.trace(wire);
		}
		return grid;
	}

	private final Multimap<Position, Wire> wires = HashMultimap.create();

	private Grid() {
		/* empty */
	}

	private void trace(Wire wire) {
		var position = Position.ORIGIN;

		for (var step : wire.getSteps()) {
			var direction = step.getDirection();
			int dx = direction.getDeltaX(), dy = direction.getDeltaY();

			for (int i = 0; i < step.getCount(); i++) {
				position = position.add(dx, dy);
				wires.put(position, wire);
			}
		}
	}

	public OptionalInt getDistanceToClosestIntersection() {
		return wires.asMap().entrySet().stream()
			.filter(e -> e.getValue().size() == 2)
			.map(Map.Entry::getKey)
			.mapToInt(Position.ORIGIN::getManhattanDistance)
			.min();
	}
}
