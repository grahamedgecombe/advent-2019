package com.grahamedgecombe.advent2019.day10;

import java.util.HashSet;
import java.util.List;

public final class Grid {
	public static Grid parse(List<String> lines) {
		var height = lines.size();
		var width = lines.get(0).length();

		var asteroids = new boolean[width * height];
		for (var y = 0; y < height; y++) {
			var line = lines.get(y);
			for (var x = 0; x < width; x++) {
				char c = line.charAt(x);
				asteroids[y * width + x] = c == '#';
			}
		}

		return new Grid(width, height, asteroids);
	}

	private final int width, height;
	private final boolean[] asteroids;

	private Grid(int width, int height, boolean[] asteroids) {
		this.width = width;
		this.height = height;
		this.asteroids = asteroids;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isAsteroid(int x, int y) {
		return asteroids[y * width + x];
	}

	public int getVisibleAsteroids(int x, int y) {
		var angles = new HashSet<Tangent>();

		for (var otherY = 0; otherY < height; otherY++) {
			for (var otherX = 0; otherX < width; otherX++) {
				if (isAsteroid(otherX, otherY) && (x != otherX || y != otherY)) {
					var opposite = y - otherY;
					var adjacent = x - otherX;
					angles.add(new Tangent(opposite, adjacent).normalize());
				}
			}
		}

		return angles.size();
	}

	public int getMaxVisibleAsteroids() {
		var max = Integer.MIN_VALUE;

		for (var y = 0; y < height; y++) {
			for (var x = 0; x < width; x++) {
				if (isAsteroid(x, y)) {
					var visibleAsteroids = getVisibleAsteroids(x, y);
					max = Math.max(max, visibleAsteroids);
				}
			}
		}

		return max;
	}
}
