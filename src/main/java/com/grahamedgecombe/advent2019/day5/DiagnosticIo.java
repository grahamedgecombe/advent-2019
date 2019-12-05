package com.grahamedgecombe.advent2019.day5;

import java.util.ArrayList;
import java.util.List;

import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class DiagnosticIo implements IntcodeIo {
	private final List<Integer> outputs = new ArrayList<>();

	@Override
	public int read() {
		return 1;
	}

	@Override
	public void write(int value) {
		outputs.add(value);
	}

	public int getDiagnosticCode() {
		return outputs.get(outputs.size() - 1);
	}
}
