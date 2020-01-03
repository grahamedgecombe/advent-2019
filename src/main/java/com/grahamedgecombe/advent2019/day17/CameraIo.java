package com.grahamedgecombe.advent2019.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class CameraIo implements IntcodeIo {
	private final List<String> lines = new ArrayList<>();
	private final StringBuilder builder = new StringBuilder();

	@Override
	public OptionalLong read() {
		throw new IllegalStateException();
	}

	@Override
	public boolean write(long value) {
		if (value == '\n') {
			if (builder.length() > 0) {
				lines.add(builder.toString());
				builder.delete(0, builder.length());
			}
		} else {
			builder.append((char) value);
		}
		return false;
	}

	public Map getMap() {
		return Map.parse(lines);
	}
}
