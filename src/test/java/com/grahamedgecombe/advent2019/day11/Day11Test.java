package com.grahamedgecombe.advent2019.day11;

import java.io.IOException;
import java.util.OptionalLong;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day11Test {
	@Test
	public void testPart1() throws IOException {
		var robot = new Robot();

		assertEquals(OptionalLong.of(0), robot.read());
		robot.write(1);
		robot.write(0);

		assertEquals(OptionalLong.of(0), robot.read());
		robot.write(0);
		robot.write(0);

		assertEquals(OptionalLong.of(0), robot.read());
		robot.write(1);
		robot.write(0);

		assertEquals(OptionalLong.of(0), robot.read());
		robot.write(1);
		robot.write(0);

		assertEquals(OptionalLong.of(1), robot.read());
		robot.write(0);
		robot.write(1);

		assertEquals(OptionalLong.of(0), robot.read());
		robot.write(1);
		robot.write(0);

		assertEquals(OptionalLong.of(0), robot.read());
		robot.write(1);
		robot.write(0);

		assertEquals(6, robot.getPaintedPanels());

		assertEquals(2268, Advent.solvePart1(new Day11()));
	}

	@Test
	public void testPart2() throws IOException {
		assertEquals("\n" +
			".##..####.###..#..#.####...##..##..###.\n" +
			"#..#.#....#..#.#.#.....#....#.#..#.#..#\n" +
			"#....###..#..#.##.....#.....#.#....#..#\n" +
			"#....#....###..#.#...#......#.#....###.\n" +
			"#..#.#....#....#.#..#....#..#.#..#.#.#.\n" +
			".##..####.#....#..#.####..##...##..#..#\n", Advent.solvePart2(new Day11()));
	}
}
