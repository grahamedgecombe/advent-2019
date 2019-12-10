package com.grahamedgecombe.advent2019.day10;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.grahamedgecombe.advent2019.Advent;
import com.grahamedgecombe.advent2019.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day10Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(Optional.of(new Station(3, 4, 8)), Grid.parse(List.of(
			".#..#",
			".....",
			"#####",
			"....#",
			"...##"
		)).getStation());

		assertEquals(Optional.of(new Station(5, 8, 33)), Grid.parse(List.of(
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
		)).getStation());

		assertEquals(Optional.of(new Station(1, 2, 35)), Grid.parse(List.of(
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
		)).getStation());

		assertEquals(Optional.of(new Station(6, 3, 41)), Grid.parse(List.of(
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
		)).getStation());

		assertEquals(Optional.of(new Station(11, 13, 210)), Grid.parse(List.of(
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
		)).getStation());

		assertEquals(214, Advent.solvePart1(new Day10()));
	}

	@Test
	public void testPart2() throws IOException {
		assertEquals(Optional.of(new Position(8, 2)), Grid.parse(List.of(
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
		)).vaporizeAsteroids());

		assertEquals(502, Advent.solvePart2(new Day10()));
	}
}
