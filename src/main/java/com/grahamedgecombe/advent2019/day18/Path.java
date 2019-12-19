package com.grahamedgecombe.advent2019.day18;

import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;

public final class Path {
	public static Path fromSimpleNodes(Grid grid, List<SimpleNode> nodes) {
		var requiredKeys = ImmutableSet.<Character>builder();
		var collectedKeys = ImmutableSet.<Character>builder();

		for (var node : nodes) {
			var tile = grid.getTile(node.getPosition());
			if (Grid.isDoor(tile)) {
				requiredKeys.add(Grid.toKey(tile));
			} else if (Grid.isKey(tile)) {
				collectedKeys.add(tile);
			}
		}

		return new Path(requiredKeys.build(), collectedKeys.build(), nodes.size() - 1);
	}

	private final ImmutableSet<Character> requiredKeys, collectedKeys;
	private final int steps;

	public Path(ImmutableSet<Character> requiredKeys, ImmutableSet<Character> collectedKeys, int steps) {
		this.requiredKeys = requiredKeys;
		this.collectedKeys = collectedKeys;
		this.steps = steps;
	}

	public ImmutableSet<Character> getRequiredKeys() {
		return requiredKeys;
	}

	public ImmutableSet<Character> getCollectedKeys() {
		return collectedKeys;
	}

	public int getSteps() {
		return steps;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("requiredKeys", requiredKeys)
			.add("collectedKeys", collectedKeys)
			.add("steps", steps)
			.toString();
	}
}
