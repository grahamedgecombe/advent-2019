package com.grahamedgecombe.advent2019.intcode;

public enum Opcode {
	ADD(1, 3),
	MULTIPLY(2, 3),
	INPUT(3, 1),
	OUTPUT(4, 1),
	JUMP_IF_TRUE(5, 2),
	JUMP_IF_FALSE(6, 2),
	LESS_THAN(7, 3),
	EQUALS(8, 3),
	RELATIVE_BASE_OFFSET(9, 1),
	FINISH(99, 0);

	public static Opcode fromId(int id) {
		for (var value : values()) {
			if (value.id == id) {
				return value;
			}
		}

		throw new IllegalArgumentException();
	}

	private final int id, parameters;

	Opcode(int id, int parameters) {
		this.id = id;
		this.parameters = parameters;
	}

	public int getId() {
		return id;
	}

	public int getParameters() {
		return parameters;
	}
}
