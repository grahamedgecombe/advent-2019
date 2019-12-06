package com.grahamedgecombe.advent2019.day6;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;
import com.grahamedgecombe.advent2019.Bfs;

public final class Universe {
	private final class Node extends Bfs.Node<Node> {
		private final String id;

		public Node(String id) {
			this.id = id;
		}

		@Override
		public boolean isGoal() {
			return id.equals("SAN");
		}

		@Override
		public Iterable<Node> getNeighbours() {
			var neighbours = Sets.union(graph.predecessors(id), graph.successors(id));
			return neighbours.stream()
				.map(Node::new)
				.collect(Collectors.toList());
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
			return id.equals(node.id);
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public String toString() {
			return id;
		}
	}

	public static Universe parse(List<String> lines) {
		var builder = GraphBuilder.directed().<String>immutable();

		for (var line : lines) {
			var parts = line.split("\\)");
			builder.putEdge(parts[0], parts[1]);
		}

		return new Universe(builder.build());
	}

	private final ImmutableGraph<String> graph;

	private Universe(ImmutableGraph<String> graph) {
		this.graph = graph;
	}

	private int getOrbits(String node, int depth) {
		int orbits = depth;
		for (var child : graph.successors(node)) {
			orbits += getOrbits(child, depth + 1);
		}
		return orbits;
	}

	public int getOrbits() {
		return getOrbits("COM", 0);
	}

	public int getTransfers() {
		var path = Bfs.search(new Node("YOU")).orElseThrow();
		/* subtract two to account for YOU/SAN, subtract one as we're counting edges not nodes */
		return path.size() - 3;
	}
}
