package com.grahamedgecombe.advent2019.day20;

import java.util.ArrayList;
import java.util.Objects;

import com.grahamedgecombe.advent2019.Bfs;
import com.grahamedgecombe.advent2019.Direction;
import com.grahamedgecombe.advent2019.Vector2;

public final class Node extends Bfs.Node<Node> {
	private final Grid grid;
	private final Vector2 position;

	public Node(Grid grid, Vector2 position) {
		this.grid = grid;
		this.position = position;
	}

	@Override
	public boolean isGoal() {
		return position.equals(grid.getPosition("ZZ").orElseThrow());
	}

	@Override
	public Iterable<Node> getNeighbours() {
		var neighbours = new ArrayList<Node>();

		for (var direction : Direction.values()) {
			var nextPosition = position.add(direction);

			var tile = grid.getTile(nextPosition);
			if (tile == '.') {
				neighbours.add(new Node(grid, nextPosition));
			}
		}

		grid.getDestination(position).ifPresent(dest -> {
			neighbours.add(new Node(grid, dest));
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
		return position.equals(node.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position);
	}
}
