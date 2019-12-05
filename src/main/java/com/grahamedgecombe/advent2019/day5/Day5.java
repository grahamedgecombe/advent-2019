package com.grahamedgecombe.advent2019.day5;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day5 extends Day<List<Integer>> {
	public Day5() {
		super(5);
	}

	@Override
	public List<Integer> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Integer> input) {
		var io = new DiagnosticIo(1);
		var machine = new IntcodeMachine(input, io);
		machine.evaluate();
		return io.getDiagnosticCode();
	}

	@Override
	public Object solvePart2(List<Integer> input) {
		var io = new DiagnosticIo(5);
		var machine = new IntcodeMachine(input, io);
		machine.evaluate();
		return io.getDiagnosticCode();
	}
}
