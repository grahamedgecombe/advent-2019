package com.grahamedgecombe.advent2019;

import com.grahamedgecombe.advent2019.day17.Map;

public enum Direction {
	LEFT('L', -1, 0, 3, Map.TILE_ROBOT_LEFT),
	UP('U', 0, 1, 1, Map.TILE_ROBOT_DOWN), /* day 17's y axis is flipped */
	RIGHT('R', 1, 0, 4, Map.TILE_ROBOT_RIGHT),
	DOWN('D', 0, -1, 2, Map.TILE_ROBOT_UP);

	public static Direction fromChar(char c) {
		for (var direction : values()) {
			if (direction.c == c) {
				return direction;
			}
		}

		throw new IllegalArgumentException();
	}

	public static Direction fromDay17Direction(char c) {
		for (var direction : values()) {
			if (direction.day17Direction == c) {
				return direction;
			}
		}

		throw new IllegalArgumentException();
	}

	private final char c;
	private final int dx, dy;
	private final int day15Command;
	private final char day17Direction;

	Direction(char c, int dx, int dy, int day15Command, char day17Direction) {
		this.c = c;
		this.dx = dx;
		this.dy = dy;
		this.day15Command = day15Command;
		this.day17Direction = day17Direction;
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

	public char getDay17Direction() {
		return day17Direction;
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

	public Direction getReverse() {
		var values = values();
		var index = (ordinal() + 2) % values.length;
		return values[index];
	}

	@Override
	public String toString() {
		return Character.toString(c);
	}
}
