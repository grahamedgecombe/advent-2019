package com.grahamedgecombe.advent2019.intcode;

public final class Parameter {
	private final ParameterMode mode;
	private final long parameter;

	public Parameter(ParameterMode mode, long parameter) {
		this.mode = mode;
		this.parameter = parameter;
	}

	public ParameterMode getMode() {
		return mode;
	}

	public long getParameter() {
		return parameter;
	}
}
