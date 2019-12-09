package com.grahamedgecombe.advent2019.day9;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.grahamedgecombe.advent2019.Advent;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day9Test {
	@Test
	public void testQuine() {
		var program = IntcodeMachine.program(109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99);
		var io = new TestIntcodeIo();
		var machine = new IntcodeMachine(program, io);
		machine.evaluate();
		assertEquals(program, io.getOutput());
	}

	private static Stream<Arguments> createLongTests() {
		return Stream.of(
			Arguments.of(1219070632396864L, IntcodeMachine.program(1102, 34915192, 34915192, 7, 4, 7, 99, 0)),
			Arguments.of(1125899906842624L, List.of(104L, 1125899906842624L, 99L))
		);
	}

	@ParameterizedTest
	@MethodSource("createLongTests")
	public void testLong(long expected, List<Long> program) {
		var io = new TestIntcodeIo();
		var machine = new IntcodeMachine(program, io);
		machine.evaluate();
		assertEquals(List.of(expected), io.getOutput());
	}

	@Test
	public void testPart1() throws IOException {
		assertEquals(2350741403L, Advent.solvePart1(new Day9()));
	}

	@Test
	public void testPart2() throws IOException {
		assertEquals(53088L, Advent.solvePart2(new Day9()));
	}
}
