package com.grahamedgecombe.advent2019.day12;

import java.io.IOException;
import java.util.List;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day12Test {
	@Test
	public void testPart1() throws IOException {
		var planet = Planet.parse(List.of(
			"<x=-1, y=0, z=2>",
			"<x=2, y=-10, z=-7>",
			"<x=4, y=-8, z=8>",
			"<x=3, y=5, z=-1>"
		));
		planet.tick(10);
		assertEquals(179, planet.getEnergy());

		planet = Planet.parse(List.of(
			"<x=-8, y=-10, z=0>",
			"<x=5, y=5, z=10>",
			"<x=2, y=-7, z=3>",
			"<x=9, y=-8, z=-3>"
		));
		planet.tick(100);
		assertEquals(1940, planet.getEnergy());

		assertEquals(5517, Advent.solvePart1(new Day12()));
	}

	@Test
	public void testPart2() throws IOException {
		assertEquals(2772L, Planet.parse(List.of(
			"<x=-1, y=0, z=2>",
			"<x=2, y=-10, z=-7>",
			"<x=4, y=-8, z=8>",
			"<x=3, y=5, z=-1>"
		)).getCycleLength());

		assertEquals(4686774924L, Planet.parse(List.of(
			"<x=-8, y=-10, z=0>",
			"<x=5, y=5, z=10>",
			"<x=2, y=-7, z=3>",
			"<x=9, y=-8, z=-3>"
		)).getCycleLength());

		assertEquals(303070460651184L, Advent.solvePart2(new Day12()));
	}
}
