package com.grahamedgecombe.advent2019.day24;

import java.util.BitSet;
import java.util.List;
import java.util.Objects;

public final class Grid {
	public static Grid parse(List<String> lines) {
		var height = lines.size();
		var width = lines.get(0).length();

		var tiles = new BitSet(width * height);

		for (var y = 0; y < height; y++) {
			var line = lines.get(y);

			for (var x = 0; x < width; x++) {
				tiles.set(y * width + x, line.charAt(x) == '#');
			}
		}

		return new Grid(tiles, width, height);
	}

	private final BitSet tiles;
	private final int width, height;

	private Grid(BitSet tiles, int width, int height) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
	}

	public BitSet getTiles() {
		return (BitSet) tiles.clone();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isBug(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return false;
		} else {
			return tiles.get(y * width + x);
		}
	}

	private int countAdjacentBugs(int x, int y) {
		var bugs = 0;

		if (isBug(x - 1, y)) {
			bugs++;
		}

		if (isBug(x + 1, y)) {
			bugs++;
		}

		if (isBug(x, y - 1)) {
			bugs++;
		}

		if (isBug(x, y + 1)) {
			bugs++;
		}

		return bugs;
	}

	public Grid next() {
		var newTiles = new BitSet(width * height);

		for (int y = 0, index = 0; y < height; y++) {
			for (var x = 0; x < width; x++, index++) {
				var bug = tiles.get(index);
				var adjacentBugs = countAdjacentBugs(x, y);

				boolean newBug;
				if (bug && adjacentBugs != 1) {
					newBug = false;
				} else if (!bug && (adjacentBugs == 1 || adjacentBugs == 2)) {
					newBug = true;
				} else {
					newBug = bug;
				}

				newTiles.set(index, newBug);
			}
		}

		return new Grid(newTiles, width, height);
	}

	public int getBiodiversity() {
		var biodiversity = 0;

		for (int y = 0, index = 0; y < height; y++) {
			for (var x = 0; x < width; x++, index++) {
				if (tiles.get(index)) {
					biodiversity |= (1 << index);
				}
			}
		}

		return biodiversity;
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
			tiles.equals(grid.tiles);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tiles, width, height);
	}

	@Override
	public String toString() {
		var builder = new StringBuilder();

		for (int y = 0, index = 0; y < height; y++) {
			for (var x = 0; x < width; x++, index++) {
				builder.append(tiles.get(index));
			}
			builder.append('\n');
		}

		return builder.toString();
	}
}
