package com.grahamedgecombe.advent2019.day5;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.grahamedgecombe.advent2019.Advent;
import com.grahamedgecombe.advent2019.intcode.IntcodeIo;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day5Test {
	@Test
	public void testPart1() throws IOException {
		var machine = new IntcodeMachine(List.of(1002, 4, 3, 4, 33), IntcodeIo.UNSUPPORTED);
		machine.evaluate();
		assertEquals(List.of(1002, 4, 3, 4, 99), machine.getMemory());

		assertEquals(15097178, Advent.solvePart1(new Day5()));
	}

	private static final List<Integer> LARGER_EXAMPLE = List.of(
		3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
		1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
		999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
	);

	private static Stream<Arguments> createInstructionTests() {
		return Stream.of(
			/* equal in position mode */
			Arguments.of(List.of(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 7, 0),
			Arguments.of(List.of(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 8, 1),
			Arguments.of(List.of(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 9, 0),

			/* less than in position mode */
			Arguments.of(List.of(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), 7, 1),
			Arguments.of(List.of(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), 8, 0),
			Arguments.of(List.of(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), 9, 0),

			/* equal in immediate mode */
			Arguments.of(List.of(3, 3, 1108, -1, 8, 3, 4, 3, 99), 7, 0),
			Arguments.of(List.of(3, 3, 1108, -1, 8, 3, 4, 3, 99), 8, 1),
			Arguments.of(List.of(3, 3, 1108, -1, 8, 3, 4, 3, 99), 9, 0),

			/* less than in immediate mode */
			Arguments.of(List.of(3, 3, 1107, -1, 8, 3, 4, 3, 99), 7, 1),
			Arguments.of(List.of(3, 3, 1107, -1, 8, 3, 4, 3, 99), 8, 0),
			Arguments.of(List.of(3, 3, 1107, -1, 8, 3, 4, 3, 99), 9, 0),

			/* jump in position mode */
			Arguments.of(List.of(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), 0, 0),
			Arguments.of(List.of(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), 1, 1),

			/* jump in immediate mode */
			Arguments.of(List.of(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), 0, 0),
			Arguments.of(List.of(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), 1, 1),

			/* larger example */
			Arguments.of(LARGER_EXAMPLE, 7, 999),
			Arguments.of(LARGER_EXAMPLE, 8, 1000),
			Arguments.of(LARGER_EXAMPLE, 9, 1001)
		);
	}

	@ParameterizedTest
	@MethodSource("createInstructionTests")
	public void testInstruction(List<Integer> program, int input, int output) {
		var io = new DiagnosticIo(input);
		var machine = new IntcodeMachine(program, io);
		machine.evaluate();
		assertEquals(output, io.getDiagnosticCode());
	}

	@Test
	public void testPart2() throws IOException {
		assertEquals(1558663, Advent.solvePart2(new Day5()));
	}
}
