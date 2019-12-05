package com.grahamedgecombe.advent2019.day2;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.intcode.IntcodeIo;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day2 extends Day<List<Integer>> {
	public Day2() {
		super(2);
	}

	@Override
	public List<Integer> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Integer> input) {
		var machine = new IntcodeMachine(input, IntcodeIo.UNSUPPORTED);
		machine.poke(1, 12);
		machine.poke(2, 2);
		machine.evaluate();
		return machine.peek(0);
	}

	@Override
	public Object solvePart2(List<Integer> input) {
		for (int noun = 0; noun < 100; noun++) {
			for (int verb = 0; verb < 100; verb++) {
				var machine = new IntcodeMachine(input, IntcodeIo.UNSUPPORTED);
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
