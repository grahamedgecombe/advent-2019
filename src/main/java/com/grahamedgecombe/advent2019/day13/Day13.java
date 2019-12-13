package com.grahamedgecombe.advent2019.day13;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day13 extends Day<List<Long>> {
	public Day13() {
		super(13);
	}

	@Override
	public List<Long> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Long> input) {
		var screen = new Screen();
		var machine = new IntcodeMachine(input, screen);
		machine.evaluate();
		return screen.countBlockTiles();
	}
}
