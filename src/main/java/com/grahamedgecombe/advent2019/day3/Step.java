package com.grahamedgecombe.advent2019.day3;

import com.grahamedgecombe.advent2019.Direction;

public final class Step {
	public static Step parse(String str) {
		var direction = Direction.fromChar(str.charAt(0));
		var count = Integer.parseInt(str.substring(1));
		return new Step(direction, count);
	}

	private final Direction direction;
	private final int count;

	public Step(Direction direction, int count) {
		this.direction = direction;
		this.count = count;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getCount() {
		return count;
	}

	@Override
	public String toString() {
		return direction.toString() + count;
	}
}
