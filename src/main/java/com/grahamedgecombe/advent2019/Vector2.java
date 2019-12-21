package com.grahamedgecombe.advent2019;

import java.util.Objects;

public final class Vector2 {
	public static final Vector2 ORIGIN = new Vector2(0, 0);

	private final int x, y;

	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Vector2 add(int dx, int dy) {
		return new Vector2(x + dx, y + dy);
	}

	public Vector2 add(Vector2 other) {
		return add(other.x, other.y);
	}

	public Vector2 add(Direction direction) {
		return add(direction.getDeltaX(), direction.getDeltaY());
	}

	public int getManhattanDistance(Vector2 other) {
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
		Vector2 position = (Vector2) o;
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
