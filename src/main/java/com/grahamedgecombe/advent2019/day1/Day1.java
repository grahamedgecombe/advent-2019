package com.grahamedgecombe.advent2019.day1;

import java.util.List;
import java.util.stream.Collectors;

import com.grahamedgecombe.advent2019.Day;

public final class Day1 extends Day<List<Integer>> {
	public Day1() {
		super(1);
	}

	@Override
	public List<Integer> parse(List<String> lines) {
		return lines.stream()
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	@Override
	public Object solvePart1(List<Integer> input) {
		return input.stream()
			.mapToInt(Day1::requiredFuelPart1)
			.sum();
	}

	@Override
	public Object solvePart2(List<Integer> input) {
		return input.stream()
			.mapToInt(Day1::requiredFuelPart2)
			.sum();
	}

	public static int requiredFuelPart1(int mass) {
		return mass / 3 - 2;
	}

	public static int requiredFuelPart2(int mass) {
		var total = 0;

		int fuel;
		while ((fuel = requiredFuelPart1(mass)) > 0) {
			total += fuel;
			mass = fuel;
		}

		return total;
	}
}
