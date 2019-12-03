package com.grahamedgecombe.advent2019.day1;

import java.io.IOException;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day1Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(2, Day1.requiredFuelPart1(12));
		assertEquals(2, Day1.requiredFuelPart1(14));
		assertEquals(654, Day1.requiredFuelPart1(1969));
		assertEquals(33583, Day1.requiredFuelPart1(100756));

		Assertions.assertEquals(3311492, Advent.solvePart1(new Day1()));
	}

	@Test
	public void testPart2() throws IOException {
		assertEquals(2, Day1.requiredFuelPart2(14));
		assertEquals(966, Day1.requiredFuelPart2(1969));
		assertEquals(50346, Day1.requiredFuelPart2(100756));

		assertEquals(4964376, Advent.solvePart2(new Day1()));
	}
}
