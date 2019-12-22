package com.grahamedgecombe.advent2019.day22;

import java.util.ArrayList;
import java.util.List;

public final class DealWithIncrement implements Technique {
	private final int n;

	public DealWithIncrement(int n) {
		this.n = n;
	}

	@Override
	public List<Integer> run(List<Integer> cards) {
		var newCards = new ArrayList<Integer>();
		for (var i = 0; i < cards.size(); i++) {
			newCards.add(null);
		}

		var pos = 0;
		for (var card : cards) {
			newCards.set(pos, card);
			pos = (pos + n) % cards.size();
		}

		return newCards;
	}
}
