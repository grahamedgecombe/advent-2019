package com.grahamedgecombe.advent2019.day4;

import java.io.IOException;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class Day4Test {
	@Test
	public void testPart1() throws IOException {
		assertTrue(Day4.isValid(111111, false));
		assertFalse(Day4.isValid(223450, false));
		assertFalse(Day4.isValid(123789, false));

		assertEquals(1864, Advent.solvePart1(new Day4()));
	}

	@Test
	public void testPart2() throws IOException {
		assertTrue(Day4.isValid(112233, true));
		assertFalse(Day4.isValid(123444, true));
		assertTrue(Day4.isValid(111122, true));

		assertEquals(1258, Advent.solvePart2(new Day4()));
	}
}
