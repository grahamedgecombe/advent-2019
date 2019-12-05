package com.grahamedgecombe.advent2019.intcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;

public final class IntcodeMachine {
	public static final List<Integer> parseProgram(String line) {
		return Arrays.stream(line.split(","))
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	private final List<Integer> memory;
	private final IntcodeIo io;
	private int pc = 0;

	public IntcodeMachine(List<Integer> memory, IntcodeIo io) {
		this.memory = new ArrayList<>(memory);
		this.io = io;
	}

	public void poke(int addr, int value) {
		memory.set(addr, value);
	}

	public int peek(int addr) {
		return memory.get(addr);
	}

	public List<Integer> getMemory() {
		return memory;
	}

	public void evaluate() {
		while (step());
	}

	private int readParameter(Parameter parameter) {
		switch (parameter.getMode()) {
		case POSITION:
			return peek(parameter.getParameter());
		case IMMEDIATE:
			return parameter.getParameter();
		}

		throw new IllegalArgumentException();
	}

	private void writeParameter(Parameter parameter, int value) {
		Preconditions.checkArgument(parameter.getMode() == ParameterMode.POSITION);
		poke(parameter.getParameter(), value);
	}

	public boolean step() {
		int insn = memory.get(pc++);

		var opcode = Opcode.fromId(insn % 100);
		insn /= 100;

		var parameters = new Parameter[opcode.getParameters()];
		for (var i = 0; i < parameters.length; i++) {
			var mode = ParameterMode.fromId(insn % 10);
			insn /= 10;

			var parameter = memory.get(pc++);

			parameters[i] = new Parameter(mode, parameter);
		}

		switch (opcode) {
		case ADD:
			var result = readParameter(parameters[0]) + readParameter(parameters[1]);
			writeParameter(parameters[2], result);
			break;
		case MULTIPLY:
			result = readParameter(parameters[0]) * readParameter(parameters[1]);
			writeParameter(parameters[2], result);
			break;
		case INPUT:
			writeParameter(parameters[0], io.read());
			break;
		case OUTPUT:
			io.write(readParameter(parameters[0]));
			break;
		case JUMP_IF_TRUE:
			if (readParameter(parameters[0]) != 0) {
				pc = readParameter(parameters[1]);
			}
			break;
		case JUMP_IF_FALSE:
			if (readParameter(parameters[0]) == 0) {
				pc = readParameter(parameters[1]);
			}
			break;
		case LESS_THAN:
			result = readParameter(parameters[0]) < readParameter(parameters[1]) ? 1 : 0;
			writeParameter(parameters[2], result);
			break;
		case EQUALS:
			result = readParameter(parameters[0]) == readParameter(parameters[1]) ? 1 : 0;
			writeParameter(parameters[2], result);
			break;
		case FINISH:
			return false;
		}

		return true;
	}
}
