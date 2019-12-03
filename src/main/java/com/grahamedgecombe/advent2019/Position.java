package com.grahamedgecombe.advent2019;

import java.util.Objects;

public final class Position {
	public static final Position ORIGIN = new Position(0, 0);

	private final int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position add(int dx, int dy) {
		return new Position(x + dx, y + dy);
	}

	public int getManhattanDistance(Position other) {
		return Math.abs(x - other.x) + Math.abs(y - other.y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Position position = (Position) o;
		return x == position.x &&
			y == position.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}
