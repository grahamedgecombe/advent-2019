package com.grahamedgecombe.advent2019.day22;

import java.util.Collections;
import java.util.List;

public final class DealIntoNewStack implements Technique {
	public static final Technique INSTANCE = new DealIntoNewStack();

	private DealIntoNewStack() {
		/* empty */
	}

	@Override
	public List<Integer> run(List<Integer> cards) {
		Collections.reverse(cards);
		return cards;
	}

	@Override
	public int getA() {
		return -1;
	}

	@Override
	public int getB() {
		return -1;
	}
}
