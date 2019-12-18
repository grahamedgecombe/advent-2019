package com.grahamedgecombe.advent2019.day17;

import java.util.List;

import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day17 extends Day<List<Long>> {
	private static Map readMap(List<Long> input) {
		var io = new CameraIo();
		var machine = new IntcodeMachine(input, io);
		machine.evaluate();
		return io.getMap();
	}

	public Day17() {
		super(17);
	}

	@Override
	public List<Long> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Long> input) {
		return readMap(input).getAlignmentParameterSum();
	}

	@Override
	public Object solvePart2(List<Long> input) {
		var map = readMap(input);
		var path = CompressedPath.compress(map.calculatePath());

		var io = new RobotIo(path);
		var machine = new IntcodeMachine(input, io);
		machine.poke(0, 2);
		machine.evaluate();
		return io.getDust();
	}
}
