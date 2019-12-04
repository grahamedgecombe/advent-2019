package com.grahamedgecombe.advent2019.day4;

import java.io.IOException;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class Day4Test {
	@Test
	public void testPart1() throws IOException {
		assertTrue(Day4.isValid(111111));
		assertFalse(Day4.isValid(223450));
		assertFalse(Day4.isValid(123789));

		assertEquals(1864, Advent.solvePart1(new Day4()));
	}
}
