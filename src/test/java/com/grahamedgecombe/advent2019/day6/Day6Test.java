package com.grahamedgecombe.advent2019.day6;

import java.io.IOException;
import java.util.List;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day6Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(42, Universe.parse(List.of(
			"COM)B",
			"B)C",
			"C)D",
			"D)E",
			"E)F",
			"B)G",
			"G)H",
			"D)I",
			"E)J",
			"J)K",
			"K)L"
		)).getOrbits());

		assertEquals(295936, Advent.solvePart1(new Day6()));
	}
}
