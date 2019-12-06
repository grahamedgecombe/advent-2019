package com.grahamedgecombe.advent2019.day6;

import java.util.List;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;

public final class Universe {
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
}
