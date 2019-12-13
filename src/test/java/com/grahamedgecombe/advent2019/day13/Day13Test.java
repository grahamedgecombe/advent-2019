package com.grahamedgecombe.advent2019.day13;

import java.io.IOException;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day13Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(380L, Advent.solvePart1(new Day13()));
	}
}
