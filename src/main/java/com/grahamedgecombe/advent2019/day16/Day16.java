package com.grahamedgecombe.advent2019.day16;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.grahamedgecombe.advent2019.Day;

public final class Day16 extends Day<int[]> {
	private static final int[] PATTERN = { 0, 1, 0, -1 };

	public static int[] transform(int[] list, int phases) {
		for (int i = 0; i < phases; i++) {
			list = transform(list);
		}
		return list;
	}

	private static int[] transform(int[] in) {
		var out = new int[in.length];

		for (var i = 0; i < out.length; i++) {
			var sum = 0;

			for (var j = 0; j < in.length; j++) {
				var patternIndex = (j + 1) / (i + 1);
				sum += in[j] * PATTERN[patternIndex % PATTERN.length];
			}

			out[i] = Math.abs(sum) % 10;
		}

		return out;
	}

	public Day16() {
		super(16);
	}

	@Override
	public int[] parse(List<String> lines) {
		return lines.get(0).chars()
			.map(c -> c - '0')
			.toArray();
	}

	@Override
	public Object solvePart1(int[] input) {
		var output = transform(input, 100);
		return Arrays.stream(output)
			.limit(8)
			.mapToObj(Integer::toString)
			.collect(Collectors.joining());
	}
}
