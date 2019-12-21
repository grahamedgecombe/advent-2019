package com.grahamedgecombe.advent2019.day21;

import java.util.List;

import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class SpringdroidIo implements IntcodeIo {
	private final String input;
	private int pos = 0;
	private long damage;

	public SpringdroidIo(List<String> lines) {
		this.input = String.join("\n", lines).concat("\n");
	}

	@Override
	public long read() {
		return input.charAt(pos++);
	}

	@Override
	public boolean write(long value) {
		damage = value;
		return false;
	}

	public long getDamage() {
		return damage;
	}
}
