package com.grahamedgecombe.advent2019.day20;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.grahamedgecombe.advent2019.Bfs;
import com.grahamedgecombe.advent2019.Vector2;

public final class Grid {
	private static final Vector2[][] DELTAS = {
		{
			new Vector2(0, -2),
			new Vector2(0, -1)
		},
		{
			new Vector2(0, 1),
			new Vector2(0, 2)
		},
		{
			new Vector2(-2, 0),
			new Vector2(-1, 0)
		},
		{
			new Vector2(1, 0),
			new Vector2(2, 0)
		}
	};

	public static Grid parse(List<String> lines) {
		var height = lines.size();
		var width = lines.stream().mapToInt(String::length).max().orElseThrow();

		var tiles = new char[width * height];

		for (var y = 0; y < height; y++) {
			var line = lines.get(y);

			for (var x = 0; x < width; x++) {
				var tile = x < line.length() ? line.charAt(x) : ' ';
				tiles[y * width + x] = tile;
			}
		}

		var posToName = ImmutableMap.<Vector2, String>builder();
		var nameToPos = ImmutableMultimap.<String, Vector2>builder();

		for (var y = 0; y < height; y++) {
			for (var x = 0; x < width; x++) {
				var tile = getTile(tiles, width, height, x, y);
				if (tile != '.') {
					continue;
				}

				var pos = new Vector2(x, y);

				for (var d : DELTAS) {
					var d0 = getTile(tiles, width, height, pos.add(d[0]));
					var d1 = getTile(tiles, width, height, pos.add(d[1]));

					if (!Character.isAlphabetic(d0) || !Character.isAlphabetic(d1)) {
						continue;
					}

					var name = new String(new char[] { d0, d1 });
					posToName.put(pos, name);
					nameToPos.put(name, pos);
				}
			}
		}

		return new Grid(tiles, width, height, posToName.build(), nameToPos.build());
	}

	private static char getTile(char[] tiles, int width, int height, int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return ' ';
		} else {
			return tiles[y * width + x];
		}
	}

	private static char getTile(char[] tiles, int width, int height, Vector2 pos) {
		return getTile(tiles, width, height, pos.getX(), pos.getY());
	}

	private final char[] tiles;
	private final int width, height;
	private final ImmutableMap<Vector2, String> posToName;
	private final ImmutableMultimap<String, Vector2> nameToPos;

	public Grid(char[] tiles, int width, int height, ImmutableMap<Vector2, String> posToName, ImmutableMultimap<String, Vector2> nameToPos) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
		this.posToName = posToName;
		this.nameToPos = nameToPos;
	}

	public char getTile(Vector2 pos) {
		return getTile(tiles, width, height, pos);
	}

	public Optional<Vector2> getPosition(String name) {
		return nameToPos.get(name).stream().findAny();
	}

	public Optional<Vector2> getDestination(Vector2 source) {
		var name = posToName.get(source);
		if (name == null) {
			return Optional.empty();
		}

		return nameToPos.get(name).stream()
			.filter(p -> !p.equals(source))
			.findAny();
	}

	private int getSteps(boolean recursive) {
		var start = getPosition("AA").orElseThrow();
		var path = Bfs.search(new Node(this, start, 0, recursive)).orElseThrow();
		return path.size() - 1;
	}

	public int getSteps() {
		return getSteps(false);
	}

	public int getRecursiveSteps() {
		return getSteps(true);
	}

	public boolean isInner(Vector2 pos) {
		return pos.getX() >= 3 && pos.getY() >= 3 && pos.getX() < (width - 3) && pos.getY() < (height - 3);
	}
}
