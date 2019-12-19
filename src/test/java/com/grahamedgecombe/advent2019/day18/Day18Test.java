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
}
