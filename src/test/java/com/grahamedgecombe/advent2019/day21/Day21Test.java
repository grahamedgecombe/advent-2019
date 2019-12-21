package com.grahamedgecombe.advent2019.day21;

import java.io.IOException;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day21Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(19361850L, Advent.solvePart1(new Day21()));
	}
}
