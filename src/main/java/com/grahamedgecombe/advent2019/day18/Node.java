package com.grahamedgecombe.advent2019.day18;

import java.util.ArrayList;
import java.util.Objects;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.graph.ValueGraph;
import com.grahamedgecombe.advent2019.Dijkstra;
import com.grahamedgecombe.advent2019.Vector2;

public final class Node extends Dijkstra.Node<Node> {
	private final Grid grid;
	private final ValueGraph<Vector2, Path> graph;
	private final ImmutableList<Vector2> positions;
	private final ImmutableSet<Character> keys;

	public Node(Grid grid, ValueGraph<Vector2, Path> graph, ImmutableList<Vector2> positions, ImmutableSet<Character> keys) {
		this.grid = grid;
		this.graph = graph;
		this.positions = positions;
		this.keys = keys;
	}

	@Override
	public boolean isGoal() {
		return keys.size() == grid.getKeys();
	}

	@Override
	public int getDistance(Node neighbour) {
		var steps = 0;

		for (var robot = 0; robot < positions.size(); robot++) {
			var from = positions.get(robot);
			var to = neighbour.positions.get(robot);
			if (!from.equals(to)) {
				steps += graph.edgeValue(from, to).orElseThrow().getSteps();
			}
		}

		return steps;
	}

	@Override
	public Iterable<Node> getNeighbours() {
		var neighbours = new ArrayList<Node>();

		for (var robot = 0; robot < positions.size(); robot++) {
			var position = positions.get(robot);

			for (var nextPosition : graph.successors(position)) {
				var path = graph.edgeValue(position, nextPosition).orElseThrow();

				if (!keys.containsAll(path.getRequiredKeys())) {
					continue;
				}

				if (keys.containsAll(path.getCollectedKeys())) {
					continue;
				}

				var nextPositions = ImmutableList.<Vector2>builder();
				for (var i = 0; i < positions.size(); i++) {
					nextPositions.add(i == robot ? nextPosition : positions.get(i));
				}

				var nextKeys = ImmutableSet.<Character>builder()
					.addAll(keys)
					.addAll(path.getCollectedKeys())
					.build();

				neighbours.add(new Node(grid, graph, nextPositions.build(), nextKeys));
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
