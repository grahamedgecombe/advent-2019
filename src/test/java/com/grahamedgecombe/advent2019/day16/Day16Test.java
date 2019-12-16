package com.grahamedgecombe.advent2019.day16;

import java.io.IOException;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day16Test {
	@Test
	public void testPart1() throws IOException {
		assertArrayEquals(new int[] { 0, 1, 0, 2, 9, 4, 9, 8 }, Day16.transform(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, 4));

		assertEquals("30550349", Advent.solvePart1(new Day16()));
	}
}
