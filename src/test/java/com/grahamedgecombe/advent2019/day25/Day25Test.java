package com.grahamedgecombe.advent2019.day25;

import java.io.IOException;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day25Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(134807554, Advent.solvePart1(new Day25()));
	}
}
