package com.grahamedgecombe.advent2019.day18;

import java.util.ArrayList;
import java.util.Objects;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.grahamedgecombe.advent2019.Bfs;
import com.grahamedgecombe.advent2019.Direction;
import com.grahamedgecombe.advent2019.Vector2;

public final class Node extends Bfs.Node<Node> {
	private final Grid grid;
	private final ImmutableList<Vector2> positions;
	private final ImmutableSet<Character> keys;

	public Node(Grid grid, ImmutableList<Vector2> positions, ImmutableSet<Character> keys) {
		this.grid = grid;
		this.positions = positions;
		this.keys = keys;
	}

	@Override
	public boolean isGoal() {
		return keys.size() == grid.getKeys();
	}

	@Override
	public Iterable<Node> getNeighbours() {
		var neighbours = new ArrayList<Node>();

		for (var robot = 0; robot < positions.size(); robot++) {
			var position = positions.get(robot);

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

				var nextPositions = ImmutableList.<Vector2>builder();
				for (var i = 0; i < positions.size(); i++) {
					nextPositions.add(i == robot ? nextPosition : positions.get(i));
				}

				neighbours.add(new Node(grid, nextPositions.build(), nextKeys));
			}
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
		return positions.equals(node.positions) &&
			keys.equals(node.keys);
	}

	@Override
	public int hashCode() {
		return Objects.hash(positions, keys);
	}
}
