package com.grahamedgecombe.advent2019;

public enum Direction {
	LEFT('L', -1, 0, 3),
	UP('U', 0, 1, 1),
	RIGHT('R', 1, 0, 4),
	DOWN('D', 0, -1, 2);

	public static Direction fromChar(char c) {
		for (var direction : values()) {
			if (direction.c == c) {
				return direction;
			}
		}

		throw new IllegalArgumentException();
	}

	private final char c;
	private final int dx, dy;
	private final int day15Command;

	Direction(char c, int dx, int dy, int day15Command) {
		this.c = c;
		this.dx = dx;
		this.dy = dy;
		this.day15Command = day15Command;
	}

	public int getDeltaX() {
		return dx;
	}

	public int getDeltaY() {
		return dy;
	}

	public int getDay15Command() {
		return day15Command;
	}

	public Direction getLeft() {
		var values = values();
		var index = (ordinal() + values.length - 1) % values.length;
		return values[index];
	}

	public Direction getRight() {
		var values = values();
		var index = (ordinal() + 1) % values.length;
		return values[index];
	}

	@Override
	public String toString() {
		return Character.toString(c);
	}
}
