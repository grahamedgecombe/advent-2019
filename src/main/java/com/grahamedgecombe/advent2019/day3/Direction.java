package com.grahamedgecombe.advent2019.day3;

public enum Direction {
	LEFT('L', -1, 0),
	RIGHT('R', 1, 0),
	UP('U', 0, 1),
	DOWN('D', 0, -1);

	static Direction fromChar(char c) {
		for (var direction : values()) {
			if (direction.c == c) {
				return direction;
			}
		}

		throw new IllegalArgumentException();
	}

	private final char c;
	private final int dx, dy;

	Direction(char c, int dx, int dy) {
		this.c = c;
		this.dx = dx;
		this.dy = dy;
	}

	public int getDeltaX() {
		return dx;
	}

	public int getDeltaY() {
		return dy;
	}

	@Override
	public String toString() {
		return Character.toString(c);
	}
}
