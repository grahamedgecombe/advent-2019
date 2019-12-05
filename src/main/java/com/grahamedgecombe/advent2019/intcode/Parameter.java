package com.grahamedgecombe.advent2019.intcode;

public final class Parameter {
	private final ParameterMode mode;
	private final int parameter;

	public Parameter(ParameterMode mode, int parameter) {
		this.mode = mode;
		this.parameter = parameter;
	}

	public ParameterMode getMode() {
		return mode;
	}

	public int getParameter() {
		return parameter;
	}
}
