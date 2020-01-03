package com.grahamedgecombe.advent2019.day15;

import java.util.OptionalLong;

import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class RepairDroidIo implements IntcodeIo {
	public static final int STATUS_WALL = 0;
	public static final int STATUS_CLEAR = 1;
	public static final int STATUS_OXYGEN = 2;

	private final int direction;
	private int status;

	public RepairDroidIo(int direction) {
		this.direction = direction;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public OptionalLong read() {
		return OptionalLong.of(direction);
	}

	@Override
	public boolean write(long value) {
		status = (int) value;
		return true;
	}
}
