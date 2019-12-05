package com.grahamedgecombe.advent2019.intcode;

import java.util.ArrayList;
import java.util.List;

public final class IntcodeMachine {
	private static final int OP_ADD = 1;
	private static final int OP_MULTIPLY = 2;
	private static final int OP_FINISH = 99;

	private final List<Integer> memory;
	private int pc = 0;

	public IntcodeMachine(List<Integer> memory) {
		this.memory = new ArrayList<>(memory);
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

	public boolean step() {
		int opcode = memory.get(pc);
		if (opcode == OP_FINISH) {
			return false;
		}

		int addr1 = memory.get(pc + 1);
		int addr2 = memory.get(pc + 2);
		int addr3 = memory.get(pc + 3);

		int operand1 = memory.get(addr1);
		int operand2 = memory.get(addr2);

		int result;

		switch (opcode) {
			case OP_ADD:
				result = operand1 + operand2;
				break;
			case OP_MULTIPLY:
				result = operand1 * operand2;
				break;
			default:
				throw new IllegalStateException("Illegal opcode: " + opcode);
		}

		memory.set(addr3, result);

		pc += 4;
		return true;
	}
}
