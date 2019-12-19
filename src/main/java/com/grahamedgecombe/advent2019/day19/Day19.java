package com.grahamedgecombe.advent2019.day19;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day19 extends Day<List<Long>> {
	public Day19() {
		super(19);
	}

	@Override
	public List<Long> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Long> input) {
		var affected = 0;

		for (var x = 0; x < 50; x++) {
			for (var y = 0; y < 50; y++) {
				var io = new BeamIo(x, y);
				var machine = new IntcodeMachine(input, io);
				machine.evaluate();

				if (io.isAffected()) {
					affected++;
				}
			}
		}

		return affected;
	}
}
