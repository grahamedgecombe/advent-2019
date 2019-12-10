package com.grahamedgecombe.advent2019.day10;

import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import com.grahamedgecombe.advent2019.Position;

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

	public SortedMap<Tangent, SortedMap<Integer, Position>> getAsteroids(int x, int y) {
		var asteroids = new TreeMap<Tangent, SortedMap<Integer, Position>>();

		for (var otherY = 0; otherY < height; otherY++) {
			for (var otherX = 0; otherX < width; otherX++) {
				if (isAsteroid(otherX, otherY) && (x != otherX || y != otherY)) {
					var opposite = otherY - y;
					var adjacent = otherX - x;

					var angle = new Tangent(opposite, adjacent).normalize();
					var squaredDistance = opposite * opposite + adjacent * adjacent;
					asteroids.computeIfAbsent(angle, a -> new TreeMap<>()).put(squaredDistance, new Position(otherX, otherY));
				}
			}
		}

		return asteroids;
	}

	public Optional<Station> getStation() {
		Station station = null;

		for (var y = 0; y < height; y++) {
			for (var x = 0; x < width; x++) {
				if (isAsteroid(x, y)) {
					var visibleAsteroids = getAsteroids(x, y).keySet().size();
					if (station == null || visibleAsteroids > station.getVisibleAsteroids()) {
						station = new Station(x, y, visibleAsteroids);
					}
				}
			}
		}

		return Optional.ofNullable(station);
	}

	public int getMaxVisibleAsteroids() {
		return getStation().orElseThrow().getVisibleAsteroids();
	}

	public Optional<Position> vaporizeAsteroids() {
		var station = getStation().orElseThrow();
		var asteroids = getAsteroids(station.getX(), station.getY());

		var n = 0;
		while (!asteroids.isEmpty()) {
			for (var it = asteroids.entrySet().iterator(); it.hasNext(); ) {
				var entry = it.next();
				var ray = entry.getValue();

				var position = ray.remove(ray.firstKey());
				if (++n == 200) {
					return Optional.of(position);
				}

				if (ray.isEmpty()) {
					it.remove();
				}
			}
		}

		return Optional.empty();
	}
}
