package com.grahamedgecombe.advent2019.day21;

import java.io.IOException;
import java.util.List;

import com.grahamedgecombe.advent2019.Advent;
import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.intcode.IntcodeIo;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day21 extends Day<List<Long>> {
	public Day21() {
		super(21);
	}

	@Override
	public List<Long> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Long> input) {
		/*
		 * The springdroid always jumps 4 tiles.
		 *
		 * It can therefore jump across any combination of gaps in A/B/C as
		 * long as D is ground, so our program is:
		 *
		 *   J = (~A | ~B | ~C) & D
		 *
		 * Simplify with De Morgan's law:
		 *
		 *   J = ~(A & B & C) & D
		 */
		var io = new SpringdroidIo(List.of(
			"OR A J",  /* J  =  A */
			"AND B J", /* J &=  B */
			"AND C J", /* J &=  C */
			"NOT J J", /* J  = ~J */
			"AND D J", /* J &=  D */
			"WALK"
		));
		var machine = new IntcodeMachine(input, io);
		machine.evaluate();
		return io.getDamage();
	}

	@Override
	public Object solvePart2(List<Long> input) {
		new IntcodeMachine(input, IntcodeIo.INTERACTIVE).evaluate();
		return super.solvePart2(input);
	}

	public static void main(String[] args) throws IOException {
		Advent.solvePart2(new Day21());
	}
}
