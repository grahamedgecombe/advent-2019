package com.grahamedgecombe.advent2019.day19;

import java.io.IOException;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day19Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(199, Advent.solvePart1(new Day19()));
	}
}
