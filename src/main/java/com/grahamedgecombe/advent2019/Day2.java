package com.grahamedgecombe.advent2019;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Day2 extends Day<List<Integer>> {
	public Day2() {
		super(2);
	}

	@Override
	public List<Integer> parse(List<String> lines) {
		return Arrays.stream(lines.get(0).split(","))
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	@Override
	public Object solvePart1(List<Integer> input) {
		var machine = new IntcodeMachine(input);
		machine.poke(1, 12);
		machine.poke(2, 2);
		machine.evaluate();
		return machine.peek(0);
	}

	@Override
	public Object solvePart2(List<Integer> input) {
		for (int noun = 0; noun < 100; noun++) {
			for (int verb = 0; verb < 100; verb++) {
				var machine = new IntcodeMachine(input);
				machine.poke(1, noun);
				machine.poke(2, verb);
				machine.evaluate();

				int output = machine.peek(0);
				if (output == 19690720) {
					return noun * 100 + verb;
				}
			}
		}

		throw new IllegalArgumentException("No solution");
	}
}
