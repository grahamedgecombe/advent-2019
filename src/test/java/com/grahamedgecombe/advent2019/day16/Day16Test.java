package com.grahamedgecombe.advent2019.day16;

import java.io.IOException;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day16Test {
	@Test
	public void testPart1() throws IOException {
		assertEquals("01029498", Day16.transformPart1("12345678", 4));
		assertEquals("24176176", Day16.transformPart1("80871224585914546619083218645595", 100));
		assertEquals("73745418", Day16.transformPart1("19617804207202209144916044189917", 100));
		assertEquals("52432133", Day16.transformPart1("69317163492948606335995924319873", 100));
		assertEquals("30550349", Advent.solvePart1(new Day16()));
	}

	@Test
	public void testPart2() throws IOException {
		assertEquals("84462026", Day16.transformPart2("03036732577212944063491565474664", 100, 10000));
		assertEquals("78725270", Day16.transformPart2("02935109699940807407585447034323", 100, 10000));
		assertEquals("53553731", Day16.transformPart2("03081770884921959731165446850517", 100, 10000));
		assertEquals("62938399", Advent.solvePart2(new Day16()));
	}
}
