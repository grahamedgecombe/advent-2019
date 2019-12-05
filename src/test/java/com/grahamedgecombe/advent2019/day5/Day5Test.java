package com.grahamedgecombe.advent2019.day5;

import java.io.IOException;
import java.util.List;

import com.grahamedgecombe.advent2019.Advent;
import com.grahamedgecombe.advent2019.intcode.IntcodeIo;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day5Test {
	@Test
	public void testPart1() throws IOException {
		var machine = new IntcodeMachine(List.of(1002, 4, 3, 4, 33), IntcodeIo.UNSUPPORTED);
		machine.evaluate();
		assertEquals(List.of(1002, 4, 3, 4, 99), machine.getMemory());

		assertEquals(15097178, Advent.solvePart1(new Day5()));
	}
}
