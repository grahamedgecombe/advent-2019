package com.grahamedgecombe.advent2019.day17;

import java.io.IOException;
import java.util.List;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day17Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(76, Map.parse(List.of(
			"..#..........",
			"..#..........",
			"#######...###",
			"#.#...#...#.#",
			"#############",
			"..#...#...#..",
			"..#####...^.."
		)).getAlignmentParameterSum());

		assertEquals(3336, Advent.solvePart1(new Day17()));
	}

	@Test
	public void testPart2() throws IOException {
		var path = CompressedPath.rle(Map.parse(List.of(
			"#######...#####",
			"#.....#...#...#",
			"#.....#...#...#",
			"......#...#...#",
			"......#...###.#",
			"......#.....#.#",
			"^########...#.#",
			"......#.#...#.#",
			"......#########",
			"........#...#..",
			"....#########..",
			"....#...#......",
			"....#...#......",
			"....#...#......",
			"....#####......"
		)).calculatePath());
		assertEquals("R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2", String.join(",", path));

		assertEquals(597517L, Advent.solvePart2(new Day17()));
	}
}
