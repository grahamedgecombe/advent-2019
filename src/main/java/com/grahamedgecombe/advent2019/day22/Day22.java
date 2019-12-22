package com.grahamedgecombe.advent2019.day22;

import java.util.ArrayList;
import java.util.List;

import com.grahamedgecombe.advent2019.Day;

public final class Day22 extends Day<List<Technique>> {
	public static List<Integer> createDeck(int n) {
		var deck = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			deck.add(i);
		}
		return deck;
	}

	public Day22() {
		super(22);
	}

	@Override
	public List<Technique> parse(List<String> lines) {
		return Technique.parseAll(lines);
	}

	@Override
	public Object solvePart1(List<Technique> input) {
		return Technique.runAll(createDeck(10007), input).indexOf(2019);
	}
}
