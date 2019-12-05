package com.grahamedgecombe.advent2019.day2;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.grahamedgecombe.advent2019.Advent;
import com.grahamedgecombe.advent2019.intcode.IntcodeIo;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day2Test {
	private static Stream<Arguments> createStates() {
		return Stream.of(
			Arguments.of(List.of(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50), List.of(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50)),
			Arguments.of(List.of(1, 0, 0, 0, 99), List.of(2, 0, 0, 0, 99)),
			Arguments.of(List.of(2, 3, 0, 3, 99), List.of(2, 3, 0, 6, 99)),
			Arguments.of(List.of(2, 4, 4, 5, 99, 0), List.of(2, 4, 4, 5, 99, 9801)),
			Arguments.of(List.of(1, 1, 1, 4, 99, 5, 6, 0, 99), List.of(30, 1, 1, 4, 2, 5, 6, 0, 99))
		);
	}

	@ParameterizedTest
	@MethodSource("createStates")
	public void testIntcodeMachine(List<Integer> initialState, List<Integer> finalState) {
		var machine = new IntcodeMachine(initialState, IntcodeIo.UNSUPPORTED);
		machine.evaluate();
		assertEquals(finalState, machine.getMemory());
	}

	@Test
	public void testPart1() throws IOException {
		Assertions.assertEquals(4138687, Advent.solvePart1(new Day2()));
	}

	@Test
	public void testPart2() throws IOException {
		assertEquals(6635, Advent.solvePart2(new Day2()));
	}
}
