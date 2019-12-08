package com.grahamedgecombe.advent2019.day8;

import java.io.IOException;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day8Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals(1, Image.parse(3, 2, "123456789012").findFewestZeroDigits());
		assertEquals(1474, Advent.solvePart1(new Day8()));
	}

	@Test
	public void testPart2() throws IOException {
		assertEquals(" #\n# \n", Image.parse(2, 2, "0222112222120000").combineLayers().toString());
		assertEquals("\n" +
			"  ##  ##  ###   ##  ###  \n" +
			"   # #  # #  # #  # #  # \n" +
			"   # #    #  # #    ###  \n" +
			"   # #    ###  #    #  # \n" +
			"#  # #  # # #  #  # #  # \n" +
			" ##   ##  #  #  ##  ###  \n", Advent.solvePart2(new Day8()));
	}
}
