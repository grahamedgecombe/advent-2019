package com.grahamedgecombe.advent2019.day18;

import java.util.HashSet;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.grahamedgecombe.advent2019.Bfs;
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
		Vector2 entrance = null;

		for (int y = 0; y < height; y++) {
			var line = lines.get(y);
			for (int x = 0; x < width; x++) {
				var c = line.charAt(x);
				tiles[y * width + x] = c;

				if (isKey(c)) {
					keys.add(c);
				} else if (c == TILE_ENTRANCE) {
					entrance = new Vector2(x, y);
				}
			}
		}

		Preconditions.checkArgument(entrance != null);

		return new Grid(tiles, width, height, keys.size(), entrance);
	}

	private final char[] tiles;
	private final int width, height, keys;
	private final Vector2 entrance;

	private Grid(char[] tiles, int width, int height, int keys, Vector2 entrance) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
		this.keys = keys;
		this.entrance = entrance;
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

	public int getSteps() {
		var path = Bfs.search(new Node(this, entrance, ImmutableSet.of())).orElseThrow();
		return path.size() - 1;
	}
}
