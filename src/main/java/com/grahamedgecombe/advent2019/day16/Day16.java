package com.grahamedgecombe.advent2019.day16;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;
import com.grahamedgecombe.advent2019.Day;

public final class Day16 extends Day<String> {
	private static final int[] PATTERN = { 0, 1, 0, -1 };

	private static int[] parse(String line) {
		return line.chars()
			.map(c -> c - '0')
			.toArray();
	}

	private static String getEightDigits(int[] list) {
		return Arrays.stream(list)
			.limit(8)
			.mapToObj(Integer::toString)
			.collect(Collectors.joining());
	}

	public static String transformPart1(String line, int phases) {
		var list = parse(line);
		for (int i = 0; i < phases; i++) {
			list = transformPart1(list);
		}
		return getEightDigits(list);
	}

	private static int[] transformPart1(int[] in) {
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

	private static int[] repeat(int[] in, int offset, int repeat) {
		var out = new int[in.length * repeat - offset];
		for (var i = 0; i < out.length; i++) {
			out[i] = in[(i + offset) % in.length];
		}
		return out;
	}

	public static String transformPart2(String line, int phases, int repeat) {
		var list = parse(line);
		var offset = Integer.parseInt(line.substring(0, 7));

		/* the optimization in transformPart2() only works on the RHS */
		Preconditions.checkArgument(offset >= (list.length * repeat) / 2);

		/*
		 * The left-hand side of the pattern always forms a triangle of zeroes:
		 *
		 * digit 1: 1 ...
		 * digit 2: 0 1 ...
		 * digit 3: 0 0 0 1 ...
		 * digit 4: 0 0 0 0 1 ...
		 *
		 * This means digits are only affected by their previous value of
		 * themselves and any digits to the right. They are not affected by
		 * digits to the left, so we can cut off digits from the left-hand side
		 * and the output remains the same.
		 *
		 * Cutting off everything to the left of the offset reduces the input
		 * size (in my case) by a factor of 10.
		 *
		 * This is implemented in repeat(), which handles repeating the input
		 * and then subsequently cutting it in one pass (to reduce intermediate
		 * memory usage).
		 */
		list = repeat(list, offset, repeat);

		for (int i = 0; i < phases; i++) {
			list = transformPart2(list);
		}
		return getEightDigits(list);
	}

	private static int[] transformPart2(int[] input) {
		var output = new int[input.length];

		/*
		 * The part 1 algorithm is essentially matrix/vector multiplication,
		 * where the matrix is dynamically generated based on the pattern.
		 *
		 * The 8x8 matrix (for example) looks something like this (? marks
		 * numbers we don't really care about):
		 *
		 *  1 ? ? ? ? ? ? ?
		 *  0 1 ? ? ? ? ? ?
		 *  0 0 1 ? ? ? ? ?
		 *  0 0 0 1 ? ? ? ?
		 *  0 0 0 0 1 1 1 1
		 *  0 0 0 0 0 1 1 1
		 *  0 0 0 0 0 0 1 1
		 *  0 0 0 0 0 0 0 1
		 *
		 * As well as the triangle of zeroes (noted earlier), note that the
		 * bottom right quarter of the matrix is a triangle of ones.
		 *
		 * This makes the 8x8 transform look something like this for the RHS of
		 * the output vector, assuming our input vector is
		 * (a, b, c, d, e, f, g, h):
		 *
		 * e' = e + f + g + h = e + f'
		 * f' =     f + g + h = f + g'
		 * g' =         g + h = g + h'
		 * h' =             h = h
		 *
		 * where + is defined as addition modulo 10.
		 *
		 * If we index our vector with numbers instead of letters, this is
		 * just:
		 *
		 * out[i] = in[i] + out[i + 1]
		 *
		 * with a base case of 0 for out[len] (len=8 in this case).
		 */
		for (var i = output.length - 1; i >= 0; i--) {
			var up = input[i];
			var right = i != output.length - 1 ? output[i + 1] : 0;
			output[i] = Math.abs(up + right) % 10;
		}

		return output;
	}

	public Day16() {
		super(16);
	}

	@Override
	public String parse(List<String> lines) {
		return lines.get(0);

	}

	@Override
	public Object solvePart1(String input) {
		return transformPart1(input, 100);
	}

	@Override
	public Object solvePart2(String input) {
		return transformPart2(input, 100, 10000);
	}
}
