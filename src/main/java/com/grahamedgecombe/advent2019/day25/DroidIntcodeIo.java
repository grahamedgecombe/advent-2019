package com.grahamedgecombe.advent2019.day25;

import java.util.List;
import java.util.OptionalLong;

import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class DroidIntcodeIo implements IntcodeIo {
	private final String input;
	private final StringBuilder output = new StringBuilder();
	private int pos = 0;

	public DroidIntcodeIo(List<String> commands) {
		this.input = String.join("\n", commands).concat("\n");
	}

	@Override
	public OptionalLong read() {
		if (pos >= input.length()) {
			return OptionalLong.empty();
		} else {
			return OptionalLong.of(input.charAt(pos++));
		}
	}

	@Override
	public boolean write(long value) {
		output.append((char) value);
		return false;
	}

	public String getOutput() {
		return output.toString();
	}
}
