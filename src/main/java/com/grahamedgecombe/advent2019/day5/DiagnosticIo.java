package com.grahamedgecombe.advent2019.day5;

import java.util.ArrayList;
import java.util.List;

import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class DiagnosticIo implements IntcodeIo {
	private final List<Integer> outputs = new ArrayList<>();
	private final int system;

	public DiagnosticIo(int system) {
		this.system = system;
	}

	@Override
	public int read() {
		return system;
	}

	@Override
	public void write(int value) {
		outputs.add(value);
	}

	public int getDiagnosticCode() {
		return outputs.get(outputs.size() - 1);
	}
}
