package com.grahamedgecombe.advent2019;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Day2 {
	public static void main(String[] args) throws IOException {
		var program = Arrays.stream(AdventUtils.readString("day2.txt").split(","))
			.map(Integer::parseInt)
			.collect(Collectors.toList());

		var machine = new IntcodeMachine(program);
		machine.poke(1, 12);
		machine.poke(2, 2);
		machine.evaluate();
		System.out.println(machine.peek(0));

		System.out.println(solve(program, 19690720));
	}

	public static int solve(List<Integer> program, int desiredOutput) {
		for (int noun = 0; noun < 100; noun++) {
			for (int verb = 0; verb < 100; verb++) {
				var machine = new IntcodeMachine(program);
				machine.poke(1, noun);
				machine.poke(2, verb);
				machine.evaluate();

				int output = machine.peek(0);
				if (output == desiredOutput) {
					return noun * 100 + verb;
				}
			}
		}

		throw new IllegalArgumentException("No solution");
	}
}
