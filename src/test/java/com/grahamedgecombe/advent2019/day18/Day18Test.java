package com.grahamedgecombe.advent2019.day18;

import java.io.IOException;
import java.util.List;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day18Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(8, Grid.parse(List.of(
			"#########",
			"#b.A.@.a#",
			"#########"
		)).getSteps());

		assertEquals(86, Grid.parse(List.of(
			"########################",
			"#f.D.E.e.C.b.A.@.a.B.c.#",
			"######################.#",
			"#d.....................#",
			"########################"
		)).getSteps());

		assertEquals(132, Grid.parse(List.of(
			"########################",
			"#...............b.C.D.f#",
			"#.######################",
			"#.....@.a.B.c.d.A.e.F.g#",
			"########################"
		)).getSteps());

		assertEquals(136, Grid.parse(List.of(
			"#################",
			"#i.G..c...e..H.p#",
			"########.########",
			"#j.A..b...f..D.o#",
			"########@########",
			"#k.E..a...g..B.n#",
			"########.########",
			"#l.F..d...h..C.m#",
			"#################"
		)).getSteps());

		assertEquals(81, Grid.parse(List.of(
			"########################",
			"#@..............ac.GI.b#",
			"###d#e#f################",
			"###A#B#C################",
			"###g#h#i################",
			"########################"
		)).getSteps());

		assertEquals(4844, Advent.solvePart1(new Day18()));
	}

	@Test
	public void testPart2() throws IOException {
		assertEquals(Grid.parse(List.of(
			"#######",
			"#a.#Cd#",
			"##@#@##",
			"#######",
			"##@#@##",
			"#cB#Ab#",
			"#######"
		)), Grid.parse(List.of(
			"#######",
			"#a.#Cd#",
			"##...##",
			"##.@.##",
			"##...##",
			"#cB#Ab#",
			"#######"
		)).split());

		assertEquals(8, Grid.parse(List.of(
			"#######",
			"#a.#Cd#",
			"##@#@##",
			"#######",
			"##@#@##",
			"#cB#Ab#",
			"#######"
		)).getSteps());

		assertEquals(24, Grid.parse(List.of(
			"###############",
			"#d.ABC.#.....a#",
			"######@#@######",
			"###############",
			"######@#@######",
			"#b.....#.....c#",
			"###############"
		)).getSteps());

		assertEquals(32, Grid.parse(List.of(
			"#############",
			"#DcBa.#.GhKl#",
			"#.###@#@#I###",
			"#e#d#####j#k#",
			"###C#@#@###J#",
			"#fEbA.#.FgHi#",
			"#############"
		)).getSteps());

		assertEquals(72, Grid.parse(List.of(
			"#############",
			"#g#f.D#..h#l#",
			"#F###e#E###.#",
			"#dCba@#@BcIJ#",
			"#############",
			"#nK.L@#@G...#",
			"#M###N#H###.#",
			"#o#m..#i#jk.#",
			"#############"
		)).getSteps());

		assertEquals(1784, Advent.solvePart2(new Day18()));
	}
}
