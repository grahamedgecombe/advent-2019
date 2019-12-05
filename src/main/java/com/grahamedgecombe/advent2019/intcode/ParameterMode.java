package com.grahamedgecombe.advent2019.intcode;

public enum ParameterMode {
	POSITION(0),
	IMMEDIATE(1);

	public static ParameterMode fromId(int id) {
		for (var value : values()) {
			if (value.id == id) {
				return value;
			}
		}

		throw new IllegalArgumentException();
	}

	private final int id;

	ParameterMode(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
