package com.grahamedgecombe.advent2019.day17;

import java.util.List;

public final class Map {
	public static final char TILE_SCAFFOLD = '#';
	public static final char TILE_SPACE = '.';
	public static final char TILE_ROBOT_UP = '^';
	public static final char TILE_ROBOT_DOWN = 'v';
	public static final char TILE_ROBOT_LEFT = '<';
	public static final char TILE_ROBOT_RIGHT = '>';
	public static final int TILE_ROBOT_TUMBLING = 'X';

	public static Map parse(List<String> lines) {
		var height = lines.size();
		var width = lines.get(0).length();
		var tiles = new char[width * height];

		for (var y = 0; y < lines.size(); y++) {
			var line = lines.get(y);

			for (var x = 0; x < line.length(); x++) {
				tiles[y * width + x] = line.charAt(x);
			}
		}

		return new Map(tiles, width, height);
	}

	private final char[] tiles;
	private final int width, height;

	private Map(char[] tiles, int width, int height) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
	}

	public char getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return TILE_SPACE;
		}
		return tiles[y * width + x];
	}

	public int getAlignmentParameterSum() {
		var sum = 0;

		for (var x = 0; x < width; x++) {
			for (var y = 0; y < height; y++) {
				var tile = getTile(x, y);
				if (tile != TILE_SCAFFOLD) {
					continue;
				}

				var top = getTile(x, y - 1);
				var left = getTile(x - 1, y);
				var right = getTile(x + 1, y);
				var bottom = getTile(x, y + 1);
				if (top != TILE_SCAFFOLD || left != TILE_SCAFFOLD || right != TILE_SCAFFOLD || bottom != TILE_SCAFFOLD) {
					continue;
				}

				sum += x * y;
			}
		}

		return sum;
	}

	@Override
	public String toString() {
		var builder = new StringBuilder();
		for (var y = 0; y < height; y++) {
			for (var x = 0; x < width; x++) {
				builder.append(getTile(x, y));
			}
			builder.append('\n');
		}
		return builder.toString();
	}
}
