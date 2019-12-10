package com.grahamedgecombe.advent2019.day10;

import java.io.IOException;
import java.util.List;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day10Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(8, Grid.parse(List.of(
			".#..#",
			".....",
			"#####",
			"....#",
			"...##"
		)).getMaxVisibleAsteroids());

		assertEquals(33, Grid.parse(List.of(
			"......#.#.",
			"#..#.#....",
			"..#######.",
			".#.#.###..",
			".#..#.....",
			"..#....#.#",
			"#..#....#.",
			".##.#..###",
			"##...#..#.",
			".#....####"
		)).getMaxVisibleAsteroids());

		assertEquals(35, Grid.parse(List.of(
			"#.#...#.#.",
			".###....#.",
			".#....#...",
			"##.#.#.#.#",
			"....#.#.#.",
			".##..###.#",
			"..#...##..",
			"..##....##",
			"......#...",
			".####.###."
		)).getMaxVisibleAsteroids());

		assertEquals(41, Grid.parse(List.of(
			".#..#..###",
			"####.###.#",
			"....###.#.",
			"..###.##.#",
			"##.##.#.#.",
			"....###..#",
			"..#.#..#.#",
			"#..#.#.###",
			".##...##.#",
			".....#.#.."
		)).getMaxVisibleAsteroids());

		assertEquals(210, Grid.parse(List.of(
			".#..##.###...#######",
			"##.############..##.",
			".#.######.########.#",
			".###.#######.####.#.",
			"#####.##.#.##.###.##",
			"..#####..#.#########",
			"####################",
			"#.####....###.#.#.##",
			"##.#################",
			"#####.##.###..####..",
			"..######..##.#######",
			"####.##.####...##..#",
			".#####..#.######.###",
			"##...#.##########...",
			"#.##########.#######",
			".####.#.###.###.#.##",
			"....##.##.###..#####",
			".#.#.###########.###",
			"#.#.#.#####.####.###",
			"###.##.####.##.#..##"
		)).getMaxVisibleAsteroids());

		assertEquals(214, Advent.solvePart1(new Day10()));
	}
}
