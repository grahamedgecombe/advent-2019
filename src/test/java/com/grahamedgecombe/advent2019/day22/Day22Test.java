package com.grahamedgecombe.advent2019.day22;

import java.io.IOException;
import java.util.List;

import com.grahamedgecombe.advent2019.Advent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Day22Test {
	@Test
	public void testDealIntoNewStack() {
		var deck = Day22.createDeck(10);
		assertEquals(List.of(9, 8, 7, 6, 5, 4, 3, 2, 1, 0), DealIntoNewStack.INSTANCE.run(deck));
	}

	@Test
	public void testCut() {
		var deck = Day22.createDeck(10);
		assertEquals(List.of(3, 4, 5, 6, 7, 8, 9, 0, 1, 2), new Cut(3).run(deck));
	}

	@Test
	public void testNegativeCut() {
		var deck = Day22.createDeck(10);
		assertEquals(List.of(6, 7, 8, 9, 0, 1, 2, 3, 4, 5), new Cut(-4).run(deck));
	}

	@Test
	public void testDealWithIncrement() {
		var deck = Day22.createDeck(10);
		assertEquals(List.of(0, 7, 4, 1, 8, 5, 2, 9, 6, 3), new DealWithIncrement(3).run(deck));
	}

	@Test
	public void testPart1() throws IOException {
		assertEquals(List.of(0, 3, 6, 9, 2, 5, 8, 1, 4, 7), Technique.runAll(Day22.createDeck(10), Technique.parseAll(List.of(
			"deal with increment 7",
			"deal into new stack",
			"deal into new stack"
		))));

		assertEquals(List.of(3, 0, 7, 4, 1, 8, 5, 2, 9, 6), Technique.runAll(Day22.createDeck(10), Technique.parseAll(List.of(
			"cut 6",
			"deal with increment 7",
			"deal into new stack"
		))));

		assertEquals(List.of(6, 3, 0, 7, 4, 1, 8, 5, 2, 9), Technique.runAll(Day22.createDeck(10), Technique.parseAll(List.of(
			"deal with increment 7",
			"deal with increment 9",
			"cut -2"
		))));

		assertEquals(List.of(9, 2, 5, 8, 1, 4, 7, 0, 3, 6), Technique.runAll(Day22.createDeck(10), Technique.parseAll(List.of(
			"deal into new stack",
			"cut -2",
			"deal with increment 7",
			"cut 8",
			"cut -4",
			"deal with increment 7",
			"cut 3",
			"deal with increment 9",
			"deal with increment 3",
			"cut -1"
		))));

		assertEquals(3939, Advent.solvePart1(new Day22()));
	}
}
