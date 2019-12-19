package com.grahamedgecombe.advent2019.day18;

import java.util.ArrayList;
import java.util.Objects;

import com.google.common.collect.ImmutableSet;
import com.grahamedgecombe.advent2019.Bfs;
import com.grahamedgecombe.advent2019.Direction;
import com.grahamedgecombe.advent2019.Vector2;

public final class Node extends Bfs.Node<Node> {
	private final Grid grid;
	private final Vector2 position;
	private final ImmutableSet<Character> keys;

	public Node(Grid grid, Vector2 position, ImmutableSet<Character> keys) {
		this.grid = grid;
		this.position = position;
		this.keys = keys;
	}

	@Override
	public boolean isGoal() {
		return keys.size() == grid.getKeys();
	}

	@Override
	public Iterable<Node> getNeighbours() {
		var neighbours = new ArrayList<Node>();

		for (var direction : Direction.values()) {
			var nextPosition = position.add(direction);
			var nextKeys = keys;

			var tile = grid.getTile(nextPosition);
			if (tile == Grid.TILE_WALL) {
				continue;
			} else if (Grid.isKey(tile)) {
				if (!nextKeys.contains(tile)) {
					nextKeys = ImmutableSet.<Character>builder()
						.addAll(keys)
						.add(tile)
						.build();
				}
			} else if (Grid.isDoor(tile)) {
				if (!keys.contains(Grid.toKey(tile))) {
					continue;
				}
			} else if (tile != Grid.TILE_PASSAGE && tile != Grid.TILE_ENTRANCE) {
				throw new IllegalStateException();
			}

			neighbours.add(new Node(grid, nextPosition, nextKeys));
		}

		return neighbours;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Node node = (Node) o;
		return position.equals(node.position) &&
			keys.equals(node.keys);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, keys);
	}
}
