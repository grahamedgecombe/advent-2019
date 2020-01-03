package com.grahamedgecombe.advent2019.day24;

import java.io.IOException;
import java.util.List;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day24Test {
	@Test
	public void testPart1() throws IOException {
		var originalGrid = Grid.parse(List.of(
			"....#",
			"#..#.",
			"#..##",
			"..#..",
			"#...."
		));

		var grid = originalGrid.next();
		assertEquals(Grid.parse(List.of(
			"#..#.",
			"####.",
			"###.#",
			"##.##",
			".##.."
		)), grid);

		grid = grid.next();
		assertEquals(Grid.parse(List.of(
			"#####",
			"....#",
			"....#",
			"...#.",
			"#.###"
		)), grid);

		grid = grid.next();
		assertEquals(Grid.parse(List.of(
			"#....",
			"####.",
			"...##",
			"#.##.",
			".##.#"
		)), grid);

		grid = grid.next();
		assertEquals(Grid.parse(List.of(
			"####.",
			"....#",
			"##..#",
			".....",
			"##..."
		)), grid);

		var firstRepeated = Day24.getFirstRepeated(originalGrid);
		assertEquals(Grid.parse(List.of(
			".....",
			".....",
			".....",
			"#....",
			".#..."
		)), firstRepeated);

		assertEquals(2129920, firstRepeated.getBiodiversity());

		assertEquals(18844281, Advent.solvePart1(new Day24()));
	}
}
