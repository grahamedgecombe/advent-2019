package com.grahamedgecombe.advent2019.day18;

import java.util.ArrayList;
import java.util.Objects;

import com.grahamedgecombe.advent2019.Bfs;
import com.grahamedgecombe.advent2019.Direction;
import com.grahamedgecombe.advent2019.Vector2;

public final class SimpleNode extends Bfs.Node<SimpleNode> {
	private final Grid grid;
	private final Vector2 position;

	public SimpleNode(Grid grid, Vector2 position) {
		this.grid = grid;
		this.position = position;
	}

	public Vector2 getPosition() {
		return position;
	}

	@Override
	public boolean isGoal() {
		return Grid.isKey(grid.getTile(position));
	}

	@Override
	public Iterable<SimpleNode> getNeighbours() {
		var neighbours = new ArrayList<SimpleNode>();

		for (var direction : Direction.values()) {
			var nextPosition = position.add(direction);
			var nextTile = grid.getTile(nextPosition);

			if (nextTile != Grid.TILE_WALL) {
				neighbours.add(new SimpleNode(grid, nextPosition));
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
		SimpleNode that = (SimpleNode) o;
		return position.equals(that.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position);
	}
}
