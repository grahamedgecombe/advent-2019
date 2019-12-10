package com.grahamedgecombe.advent2019.day10;

import java.util.Objects;

public final class Station {
	private final int x, y, visibleAsteroids;

	public Station(int x, int y, int visibleAsteroids) {
		this.x = x;
		this.y = y;
		this.visibleAsteroids = visibleAsteroids;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getVisibleAsteroids() {
		return visibleAsteroids;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Station station = (Station) o;
		return x == station.x &&
			y == station.y &&
			visibleAsteroids == station.visibleAsteroids;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, visibleAsteroids);
	}
}
