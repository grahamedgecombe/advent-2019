package com.grahamedgecombe.advent2019.day11;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.Position;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day11 extends Day<List<Long>> {
	public Day11() {
		super(11);
	}

	@Override
	public List<Long> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Long> input) {
		var robot = new Robot();
		var machine = new IntcodeMachine(input, robot);
		machine.evaluate();
		return robot.getPaintedPanels();
	}

	@Override
	public Object solvePart2(List<Long> input) {
		var robot = new Robot();
		robot.setColor(Position.ORIGIN, Robot.WHITE);

		var machine = new IntcodeMachine(input, robot);
		machine.evaluate();
		return "\n".concat(robot.toString());
	}
}
