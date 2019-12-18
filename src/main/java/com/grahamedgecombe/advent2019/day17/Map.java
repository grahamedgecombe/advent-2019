package com.grahamedgecombe.advent2019.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.grahamedgecombe.advent2019.Direction;
import com.grahamedgecombe.advent2019.Vector2;

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

	public char getTile(Vector2 pos) {
		return getTile(pos.getX(), pos.getY());
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

	private Vector2 findRobot() {
		for (var x = 0; x < width; x++) {
			for (var y = 0; y < height; y++) {
				var tile = getTile(x, y);
				if (tile == TILE_ROBOT_UP || tile == TILE_ROBOT_DOWN || tile == TILE_ROBOT_LEFT || tile == TILE_ROBOT_RIGHT) {
					return new Vector2(x, y);
				}
			}
		}

		throw new IllegalArgumentException();
	}

	private Optional<Direction> turnToScaffold(List<String> path, Vector2 pos, Direction dir, boolean start) {
		if (getTile(pos.add(dir)) == Map.TILE_SCAFFOLD) {
			return Optional.of(dir);
		} else if (getTile(pos.add(dir.getLeft())) == Map.TILE_SCAFFOLD) {
			path.add("R");
			return Optional.of(dir.getLeft());
		} else if (getTile(pos.add(dir.getRight())) == Map.TILE_SCAFFOLD) {
			path.add("L");
			return Optional.of(dir.getRight());
		} else if (getTile(pos.add(dir.getReverse())) == Map.TILE_SCAFFOLD && start) {
			path.add("L");
			path.add("L");
			return Optional.of(dir.getReverse());
		} else {
			return Optional.empty();
		}
	}

	private Vector2 move(List<String> path, Vector2 pos, Direction dir) {
		path.add("1");
		return pos.add(dir);
	}

	public List<String> calculatePath() {
		var path = new ArrayList<String>();

		var pos = findRobot();
		var dir = Direction.fromDay17Direction(getTile(pos));

		dir = turnToScaffold(path, pos, dir, true).orElseThrow(IllegalArgumentException::new);
		pos = move(path, pos, dir);

		Optional<Direction> nextDir;
		while ((nextDir = turnToScaffold(path, pos, dir, false)).isPresent()) {
			dir = nextDir.get();
			pos = move(path, pos, dir);
		}

		return path;
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
