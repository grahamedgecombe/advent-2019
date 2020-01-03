package com.grahamedgecombe.advent2019.day24;

import java.util.BitSet;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

public final class RecursiveGrid {
	private static final int SIZE = 5;
	private static final BitSet EMPTY_BIT_SET = new BitSet();

	public static RecursiveGrid from(Grid grid) {
		Preconditions.checkArgument(grid.getWidth() == SIZE);
		Preconditions.checkArgument(grid.getHeight() == SIZE);

		var tiles = (BitSet) grid.getTiles().clone();
		tiles.clear(2 * SIZE + 2); // central tile is used for recursion

		return new RecursiveGrid(ImmutableMap.of(0, tiles), 0, 0);
	}

	private final ImmutableMap<Integer, BitSet> grids;
	private final int minDepth, maxDepth;

	public RecursiveGrid(ImmutableMap<Integer, BitSet> grids, int minDepth, int maxDepth) {
		this.grids = grids;
		this.minDepth = minDepth;
		this.maxDepth = maxDepth;
	}

	public RecursiveGrid next() {
		var nextGrids = ImmutableMap.<Integer, BitSet>builder();
		for (int depth = minDepth - 1; depth <= maxDepth + 1; depth++) {
			nextGrids.put(depth, createNextGrid(depth));
		}
		return new RecursiveGrid(nextGrids.build(), minDepth - 1, maxDepth + 1);
	}

	private boolean isBug(int depth, int x, int y) {
		Preconditions.checkArgument(x >= 0 && y >= 0 && x < SIZE && y < SIZE);
		Preconditions.checkArgument(x != 2 || y != 2);

		if (!grids.containsKey(depth)) {
			return false;
		} else {
			return grids.get(depth).get(y * SIZE + x);
		}
	}

	private int countAdjacentBugs(int depth, int x, int y) {
		var bugs = 0;

		/* above */
		if (y == 0) {
			if (isBug(depth - 1, 2, 1)) {
				bugs++;
			}
		} else if (y == 3 && x == 2) {
			for (int innerX = 0; innerX < SIZE; innerX++) {
				if (isBug(depth + 1, innerX, 4)) {
					bugs++;
				}
			}
		} else {
			if (isBug(depth, x, y - 1)) {
				bugs++;
			}
		}

		/* below */
		if (y == 1 && x == 2) {
			for (int innerX = 0; innerX < SIZE; innerX++) {
				if (isBug(depth + 1, innerX, 0)) {
					bugs++;
				}
			}
		} else if (y == 4) {
			if (isBug(depth - 1, 2, 3)) {
				bugs++;
			}
		} else {
			if (isBug(depth, x, y + 1)) {
				bugs++;
			}
		}

		/* left */
		if (x == 0) {
			if (isBug(depth - 1, 1, 2)) {
				bugs++;
			}
		} else if (x == 3 && y == 2) {
			for (int innerY = 0; innerY < SIZE; innerY++) {
				if (isBug(depth + 1, 4, innerY)) {
					bugs++;
				}
			}
		} else {
			if (isBug(depth, x - 1, y)) {
				bugs++;
			}
		}

		/* right */
		if (x == 1 && y == 2) {
			for (int innerY = 0; innerY < SIZE; innerY++) {
				if (isBug(depth + 1, 0, innerY)) {
					bugs++;
				}
			}
		} else if (x == 4) {
			if (isBug(depth - 1, 3, 2)) {
				bugs++;
			}
		} else {
			if (isBug(depth, x + 1, y)) {
				bugs++;
			}
		}

		return bugs;
	}

	private BitSet createNextGrid(int depth) {
		var grid = grids.getOrDefault(depth, EMPTY_BIT_SET);
		var newGrid = new BitSet(SIZE * SIZE);

		for (int y = 0, index = 0; y < SIZE; y++) {
			for (var x = 0; x < SIZE; x++, index++) {
				if (x == 2 && y == 2) {
					continue;
				}

				var bug = grid.get(index);
				var adjacentBugs = countAdjacentBugs(depth, x, y);

				boolean newBug;
				if (bug && adjacentBugs != 1) {
					newBug = false;
				} else if (!bug && (adjacentBugs == 1 || adjacentBugs == 2)) {
					newBug = true;
				} else {
					newBug = bug;
				}

				newGrid.set(index, newBug);
			}
		}

		return newGrid;
	}

	public int countBugs() {
		return grids.values().stream()
			.mapToInt(BitSet::cardinality)
			.sum();
	}

	@Override
	public String toString() {
		var builder = new StringBuilder();

		for (var depth = minDepth; depth <= maxDepth; depth++) {
			builder.append("Depth ").append(depth).append("\n");

			var grid = grids.get(depth);
			for (int y = 0, index = 0; y < SIZE; y++) {
				for (var x = 0; x < SIZE; x++, index++) {
					char tile;
					if (x == 2 && y == 2) {
						tile = '?';
					} else {
						tile = grid.get(y * SIZE + x) ? '#' : '.';
					}
					builder.append(tile);
				}

				builder.append("\n");
			}

			builder.append("\n");
		}

		return builder.toString();
	}
}
