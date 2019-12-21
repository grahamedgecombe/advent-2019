package com.grahamedgecombe.advent2019.day19;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Range;
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

	private boolean isAffected(List<Long> program, int x, int y) {
		var io = new BeamIo(x, y);
		var machine = new IntcodeMachine(program, io);
		machine.evaluate();
		return io.isAffected();
	}

	@Override
	public Object solvePart1(List<Long> input) {
		var affected = 0;

		for (var x = 0; x < 50; x++) {
			for (var y = 0; y < 50; y++) {
				if (isAffected(input, x, y)) {
					affected++;
				}
			}
		}

		return affected;
	}

	private Range<Integer> getBounds(List<Long> input, Range<Integer> previousRange, int x) {
		int previousMinY = previousRange != null ? previousRange.lowerEndpoint() : 0;
		int previousMaxY = previousRange != null ? previousRange.upperEndpoint() : 0;

		int minY;

		for (var y = previousMinY;; y++) {
			if (isAffected(input, x, y)) {
				minY = y;
				break;
			}
		}

		for (var y = Integer.max(previousMaxY, minY);; y++) {
			if (!isAffected(input, x, y)) {
				return Range.closedOpen(minY, y);
			}
		}
	}

	@Override
	public Object solvePart2(List<Long> input) {
		Range<Integer> bounds = null;
		var prevBounds = new HashMap<Integer, Range<Integer>>();

		for (var x = 10;; x++) {
			bounds = getBounds(input, bounds, x);
			prevBounds.put(x, bounds);

			if (prevBounds.containsKey(x - 99)) {
				if ((bounds.lowerEndpoint() + 100) == prevBounds.get(x - 99).upperEndpoint()) {
					return ((x - 99) * 10000) + bounds.lowerEndpoint();
				}
			}
		}
	}
}
