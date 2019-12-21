package com.grahamedgecombe.advent2019.day20;

import java.util.ArrayList;
import java.util.Objects;

import com.grahamedgecombe.advent2019.Bfs;
import com.grahamedgecombe.advent2019.Direction;
import com.grahamedgecombe.advent2019.Vector2;

public final class Node extends Bfs.Node<Node> {
	private final Grid grid;
	private final Vector2 position;
	private final int level;
	private final boolean recursive;

	public Node(Grid grid, Vector2 position, int level, boolean recursive) {
		this.grid = grid;
		this.position = position;
		this.level = level;
		this.recursive = recursive;
	}

	@Override
	public boolean isGoal() {
		return position.equals(grid.getPosition("ZZ").orElseThrow()) && level == 0;
	}

	@Override
	public Iterable<Node> getNeighbours() {
		var neighbours = new ArrayList<Node>();

		for (var direction : Direction.values()) {
			var nextPosition = position.add(direction);

			var tile = grid.getTile(nextPosition);
			if (tile == '.') {
				neighbours.add(new Node(grid, nextPosition, level, recursive));
			}
		}

		grid.getDestination(position).ifPresent(dest -> {
			var nextLevel = level;

			if (recursive) {
				if (grid.isInner(position)) {
					nextLevel++;
				} else {
					nextLevel--;
				}
			}

			if (nextLevel >= 0) {
				neighbours.add(new Node(grid, dest, nextLevel, recursive));
			}
		});

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
		return position.equals(node.position) && level == node.level;
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, level);
	}
}
