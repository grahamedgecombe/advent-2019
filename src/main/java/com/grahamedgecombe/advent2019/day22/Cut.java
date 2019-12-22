package com.grahamedgecombe.advent2019.day22;

import java.util.ArrayList;
import java.util.List;

public final class Cut implements Technique {
	private final int n;

	public Cut(int n) {
		this.n = n;
	}

	@Override
	public List<Integer> run(List<Integer> cards) {
		if (n > 0) {
			var cut = cards.subList(0, n);
			var cutCopy = new ArrayList<>(cut);
			cut.clear();
			cards.addAll(cutCopy);
		} else if (n < 0) {
			var cut = cards.subList(cards.size() + n, cards.size());
			var cutCopy = new ArrayList<>(cut);
			cut.clear();
			cards.addAll(0, cutCopy);
		}

		return cards;
	}
}
