package com.grahamedgecombe.advent2019.day9;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.day5.DiagnosticIo;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day9 extends Day<List<Long>> {
	public Day9() {
		super(9);
	}

	@Override
	public List<Long> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Long> input) {
		var io = new DiagnosticIo(1);
		var machine = new IntcodeMachine(input, io);
		machine.evaluate();
		return io.getDiagnosticCode();
	}

	@Override
	public Object solvePart2(List<Long> input) {
		var io = new DiagnosticIo(2);
		var machine = new IntcodeMachine(input, io);
		machine.evaluate();
		return io.getDiagnosticCode();
	}
}
