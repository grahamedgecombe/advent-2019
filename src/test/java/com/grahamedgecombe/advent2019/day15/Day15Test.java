package com.grahamedgecombe.advent2019.day15;

import java.io.IOException;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day15Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(234, Advent.solvePart1(new Day15()));
	}

	@Test
	public void testPart2() throws IOException {
		assertEquals(292, Advent.solvePart2(new Day15()));
	}
}
