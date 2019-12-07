package com.grahamedgecombe.advent2019.day7;

import java.util.List;

import com.google.common.collect.Collections2;
import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day7 extends Day<List<Integer>> {
	public static int getMaxSignal(List<Integer> program) {
		return Collections2.permutations(List.of(0, 1, 2, 3, 4)).stream()
			.mapToInt(phases -> getSignal(program, phases))
			.max()
			.orElseThrow();
	}

	private static int getSignal(List<Integer> program, List<Integer> phases) {
		var signal = 0;

		for (int phase : phases) {
			var io = new AmplifierIo(phase, signal);
			var machine = new IntcodeMachine(program, io);
			machine.evaluate();
			signal = io.getOutputSignal();
		}

		return signal;
	}

	public Day7() {
		super(7);
	}

	@Override
	public List<Integer> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Integer> input) {
		return getMaxSignal(input);
	}
}
