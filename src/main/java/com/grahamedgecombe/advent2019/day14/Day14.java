package com.grahamedgecombe.advent2019.day14;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;

public final class Day14 extends Day<Nanofactory> {
	public Day14() {
		super(14);
	}

	@Override
	public Nanofactory parse(List<String> lines) {
		return Nanofactory.parse(lines);
	}

	@Override
	public Object solvePart1(Nanofactory input) {
		return input.getRequiredOre();
	}

	@Override
	public Object solvePart2(Nanofactory input) {
		return input.getMaximumFuel();
	}
}
