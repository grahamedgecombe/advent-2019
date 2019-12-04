package com.grahamedgecombe.advent2019.day4;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;

public final class Day4 extends Day<Range> {
	public static boolean isValid(int number, boolean part2) {
		var s = Integer.toString(number);
		if (s.length() != 6) {
			return false;
		}

		var adjacentDigits = false;
		var runLength = 1;

		for (var i = 0; i < s.length() - 1; i++) {
			var digit1 = s.charAt(i) - '0';
			var digit2 = s.charAt(i + 1) - '0';

			if (digit1 == digit2) {
				if (!part2) {
					adjacentDigits = true;
				}

				runLength++;
			} else {
				if (part2 && runLength == 2) {
					adjacentDigits = true;
				}

				runLength = 1;
			}

			if (digit1 > digit2) {
				return false;
			}
		}

		if (part2 && runLength == 2) {
			adjacentDigits = true;
		}

		return adjacentDigits;
	}

	public Day4() {
		super(4);
	}

	@Override
	public Range parse(List<String> lines) {
		return Range.parse(lines.get(0));
	}

	private int countMatches(Range input, boolean part2) {
		var matches = 0;
		for (int i = input.getMin(); i <= input.getMax(); i++) {
			if (isValid(i, part2)) {
				matches++;
			}
		}
		return matches;
	}

	@Override
	public Object solvePart1(Range input) {
		return countMatches(input, false);
	}

	@Override
	public Object solvePart2(Range input) {
		return countMatches(input, true);
	}
}
