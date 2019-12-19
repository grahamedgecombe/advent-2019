package com.grahamedgecombe.advent2019.day18;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.grahamedgecombe.advent2019.Bfs;
import com.grahamedgecombe.advent2019.Dijkstra;
import com.grahamedgecombe.advent2019.Vector2;

public final class Grid {
	public static final char TILE_ENTRANCE = '@';
	public static final char TILE_PASSAGE = '.';
	public static final char TILE_WALL = '#';

	public static boolean isKey(char c) {
		return Character.isLowerCase(c);
	}

	public static boolean isDoor(char c) {
		return Character.isUpperCase(c);
	}

	public static char toKey(char c) {
		return Character.toLowerCase(c);
	}

	public static Grid parse(List<String> lines) {
		var height = lines.size();
		var width = lines.get(0).length();

		var tiles = new char[width * height];
		var keys = new HashSet<Character>();

		for (int y = 0; y < height; y++) {
			var line = lines.get(y);
			for (int x = 0; x < width; x++) {
				var c = line.charAt(x);
				tiles[y * width + x] = c;

				if (isKey(c)) {
					keys.add(c);
				}
			}
		}

		return new Grid(tiles, width, height, keys.size());
	}

	private final char[] tiles;
	private final int width, height, keys;

	private Grid(char[] tiles, int width, int height, int keys) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
		this.keys = keys;
	}

	public char getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return TILE_WALL;
		} else {
			return tiles[y * width + x];
		}
	}

	public char getTile(Vector2 position) {
		return getTile(position.getX(), position.getY());
	}

	public int getKeys() {
		return keys;
	}

	public ImmutableSet<Vector2> getEntrances() {
		var builder = ImmutableSet.<Vector2>builder();

		for (int y = 0, index = 0; y < height; y++) {
			for (var x = 0; x < width; x++, index++) {
				if (tiles[index] == TILE_ENTRANCE) {
					builder.add(new Vector2(x, y));
				}
			}
		}

		return builder.build();
	}

	public Grid split() {
		var entrance = Iterators.getOnlyElement(getEntrances().iterator());

		var x = entrance.getX();
		var y = entrance.getY();

		var newTiles = Arrays.copyOf(tiles, tiles.length);
		for (var dy = -1; dy <= 1; dy++) {
			for (var dx = -1; dx <= 1; dx++) {
				var tile = dx == 0 || dy == 0 ? '#' : '@';
				newTiles[(y + dy) * width + (x + dx)] = tile;
			}
		}

		return new Grid(newTiles, width, height, keys);
	}

	private ValueGraph<Vector2, Path> createGraph() {
		/* directed as we want entrance->key nodes but not key->entrance nodes */
		var graph = ValueGraphBuilder.directed().<Vector2, Path>build();

		for (int y = 0, index = 0; y < height; y++) {
			for (var x = 0; x < width; x++, index++) {
				var tile = tiles[index];
				if (tile != Grid.TILE_ENTRANCE && !Grid.isKey(tile)) {
					continue;
				}

				var from = new Vector2(x, y);
				for (var path : Bfs.searchAll(new SimpleNode(this, from))) {
					var to = path.get(path.size() - 1);
					if (!from.equals(to.getPosition())) {
						graph.putEdgeValue(from, to.getPosition(), Path.fromSimpleNodes(this, path));
					}
				}

			}
		}

		return graph;
	}

	public int getSteps() {
		var graph = createGraph();
		var positions = ImmutableList.copyOf(getEntrances());
		var path = Dijkstra.search(new Node(this, graph, positions, ImmutableSet.of())).orElseThrow();

		var steps = 0;
		for (var i = 0; i < path.size() - 1; i++) {
			var from = path.get(i);
			var to = path.get(i + 1);
			steps += from.getDistance(to);
		}
		return steps;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Grid grid = (Grid) o;
		return width == grid.width &&
			height == grid.height &&
			Arrays.equals(tiles, grid.tiles);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(width, height);
		result = 31 * result + Arrays.hashCode(tiles);
		return result;
	}

	@Override
	public String toString() {
		var builder = new StringBuilder();

		for (int y = 0, index = 0; y < height; y++) {
			for (var x = 0; x < width; x++, index++) {
				builder.append(tiles[index]);
			}
			builder.append("\n");
		}

		return builder.toString();
	}
}
