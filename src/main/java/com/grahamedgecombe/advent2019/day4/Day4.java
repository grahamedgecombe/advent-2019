package com.grahamedgecombe.advent2019.day4;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;

public final class Day4 extends Day<Range> {
	public static boolean isValid(int number) {
		var s = Integer.toString(number);
		if (s.length() != 6) {
			return false;
		}

		var adjacentDigits = false;

		for (var i = 0; i < s.length() - 1; i++) {
			var digit1 = s.charAt(i) - '0';
			var digit2 = s.charAt(i + 1) - '0';

			if (digit1 == digit2) {
				adjacentDigits = true;
			}

			if (digit1 > digit2) {
				return false;
			}
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

	@Override
	public Object solvePart1(Range input) {
		var matches = 0;
		for (int i = input.getMin(); i <= input.getMax(); i++) {
			if (isValid(i)) {
				matches++;
			}
		}
		return matches;
	}
}
