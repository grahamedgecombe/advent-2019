package com.grahamedgecombe.advent2019.day23;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day23 extends Day<List<Long>> {
	public Day23() {
		super(23);
	}

	@Override
	public List<Long> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Long> input) {
		var network = new Network(50, false);
		return network.evaluate(input);
	}

	@Override
	public Object solvePart2(List<Long> input) {
		var network = new Network(50, true);
		return network.evaluate(input);
	}
}
