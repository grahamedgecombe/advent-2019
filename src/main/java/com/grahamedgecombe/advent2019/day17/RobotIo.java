package com.grahamedgecombe.advent2019.day17;

import java.util.OptionalLong;

import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class RobotIo implements IntcodeIo {
	private final String input;
	private int pos;
	private long dust;

	public RobotIo(CompressedPath path) {
		this.input = String.format("%s\n%s\n%s\n%s\nn\n", path.getMain(), path.getA(), path.getB(), path.getC());
	}

	@Override
	public OptionalLong read() {
		return OptionalLong.of(input.charAt(pos++));
	}

	@Override
	public boolean write(long value) {
		dust = value;
		return false;
	}

	public long getDust() {
		return dust;
	}
}
