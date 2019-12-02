package com.grahamedgecombe.advent2019;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class Day2 {
	public static void main(String[] args) throws IOException {
		var program = Arrays.stream(AdventUtils.readString("day2.txt").split(","))
			.map(Integer::parseInt)
			.collect(Collectors.toList());

		var machine = new IntcodeMachine(program);
		machine.poke(1, 12);
		machine.poke(2, 2);
		machine.evaluate();
		System.out.println(machine.peek(0));
	}
}
