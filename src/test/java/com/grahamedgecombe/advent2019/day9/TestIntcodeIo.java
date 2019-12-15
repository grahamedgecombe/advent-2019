package com.grahamedgecombe.advent2019.day9;

import java.util.ArrayList;
import java.util.List;

import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class TestIntcodeIo implements IntcodeIo {
	private final List<Long> output = new ArrayList<>();

	@Override
	public long read() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean write(long value) {
		output.add(value);
		return false;
	}

	public List<Long> getOutput() {
		return output;
	}
}
