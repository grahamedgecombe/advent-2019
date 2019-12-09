package com.grahamedgecombe.advent2019.day5;

import java.util.ArrayList;
import java.util.List;

import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class DiagnosticIo implements IntcodeIo {
	private final List<Long> outputs = new ArrayList<>();
	private final long system;

	public DiagnosticIo(long system) {
		this.system = system;
	}

	@Override
	public long read() {
		return system;
	}

	@Override
	public void write(long value) {
		outputs.add(value);
	}

	public long getDiagnosticCode() {
		return outputs.get(outputs.size() - 1);
	}
}
