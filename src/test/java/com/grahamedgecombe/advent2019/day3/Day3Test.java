package com.grahamedgecombe.advent2019.day3;

import java.io.IOException;
import java.util.List;
import java.util.OptionalInt;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day3Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(OptionalInt.of(6), Grid.trace(List.of(
			Wire.parse("R8,U5,L5,D3"),
			Wire.parse("U7,R6,D4,L4")
		)).getDistanceToClosestIntersection());

		assertEquals(OptionalInt.of(159), Grid.trace(List.of(
			Wire.parse("R75,D30,R83,U83,L12,D49,R71,U7,L72"),
			Wire.parse("U62,R66,U55,R34,D71,R55,D58,R83")
		)).getDistanceToClosestIntersection());

		assertEquals(OptionalInt.of(135), Grid.trace(List.of(
			Wire.parse("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"),
			Wire.parse("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")
		)).getDistanceToClosestIntersection());

		assertEquals(489, Advent.solvePart1(new Day3()));
	}

	@Test
	public void testPart2() throws IOException {
		assertEquals(OptionalInt.of(30), Grid.trace(List.of(
			Wire.parse("R8,U5,L5,D3"),
			Wire.parse("U7,R6,D4,L4")
		)).getSmallestDelayIntersection());

		assertEquals(OptionalInt.of(610), Grid.trace(List.of(
			Wire.parse("R75,D30,R83,U83,L12,D49,R71,U7,L72"),
			Wire.parse("U62,R66,U55,R34,D71,R55,D58,R83")
		)).getSmallestDelayIntersection());

		assertEquals(OptionalInt.of(410), Grid.trace(List.of(
			Wire.parse("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"),
			Wire.parse("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")
		)).getSmallestDelayIntersection());

		assertEquals(93654, Advent.solvePart2(new Day3()));
	}
}
