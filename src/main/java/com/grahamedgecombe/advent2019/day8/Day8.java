package com.grahamedgecombe.advent2019.day8;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;

public final class Day8 extends Day<Image> {
	public Day8() {
		super(8);
	}

	@Override
	public Image parse(List<String> lines) {
		return Image.parse(25, 6, lines.get(0));
	}

	@Override
	public Object solvePart1(Image input) {
		return input.findFewestZeroDigits();
	}
}
