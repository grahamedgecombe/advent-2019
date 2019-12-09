package com.grahamedgecombe.advent2019.intcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class IntcodeMachine {
	public static List<Long> parseProgram(String line) {
		return Arrays.stream(line.split(","))
			.map(Long::parseLong)
			.collect(Collectors.toList());
	}

	public static List<Long> program(int... ints) {
		var list = new ArrayList<Long>();
		for (var i : ints) {
			list.add((long) i);
		}
		return list;
	}

	private final List<Long> memory;
	private final IntcodeIo io;
	private long pc = 0, relativeBase = 0;

	public IntcodeMachine(List<Long> memory, IntcodeIo io) {
		this.memory = new ArrayList<>(memory);
		this.io = io;
	}

	public void poke(long addr, long value) {
		while (addr >= memory.size()) {
			memory.add(0L);
		}
		memory.set((int) addr, value);
	}

	public long peek(long addr) {
		return addr < memory.size() ? memory.get((int) addr) : 0;
	}

	public List<Long> getMemory() {
		return memory;
	}

	public void evaluate() {
		while (step());
	}

	private long readParameter(Parameter parameter) {
		switch (parameter.getMode()) {
		case POSITION:
			return peek(parameter.getParameter());
		case IMMEDIATE:
			return parameter.getParameter();
		case RELATIVE:
			return peek(relativeBase + parameter.getParameter());
		default:
			throw new IllegalArgumentException();
		}
	}

	private void writeParameter(Parameter parameter, long value) {
		switch (parameter.getMode()) {
		case POSITION:
			poke(parameter.getParameter(), value);
			break;
		case RELATIVE:
			poke(relativeBase + parameter.getParameter(), value);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	public boolean step() {
		long insn = peek(pc++);

		var opcode = Opcode.fromId((int) (insn % 100));
		insn /= 100;

		var parameters = new Parameter[opcode.getParameters()];
		for (var i = 0; i < parameters.length; i++) {
			var mode = ParameterMode.fromId((int) (insn % 10));
			insn /= 10;

			var parameter = peek(pc++);

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
		case RELATIVE_BASE_OFFSET:
			relativeBase += readParameter(parameters[0]);
			break;
		case FINISH:
			return false;
		}

		return true;
	}
}
