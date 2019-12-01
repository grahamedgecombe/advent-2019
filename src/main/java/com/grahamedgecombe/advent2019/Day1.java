package com.grahamedgecombe.advent2019;

import java.io.IOException;
import java.util.stream.Collectors;

public final class Day1 {
	public static void main(String[] args) throws IOException {
		var modules = AdventUtils.readLines("day1.txt").stream()
			.map(Integer::parseInt)
			.collect(Collectors.toList());

		var sumPart1 = modules.stream()
			.mapToInt(Day1::requiredFuelPart1)
			.sum();
		System.out.println(sumPart1);
	}

	public static int requiredFuelPart1(int mass) {
		return mass / 3 - 2;
	}
}
