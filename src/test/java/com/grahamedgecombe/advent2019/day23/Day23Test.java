package com.grahamedgecombe.advent2019.day23;

import java.io.IOException;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day23Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(24954L, Advent.solvePart1(new Day23()));
	}
}
