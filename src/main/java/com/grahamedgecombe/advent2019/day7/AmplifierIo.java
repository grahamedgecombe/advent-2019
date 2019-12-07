package com.grahamedgecombe.advent2019.day7;

import com.google.common.base.Preconditions;
import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class AmplifierIo implements IntcodeIo {
	private enum State {
		READ_PHASE,
		READ_SIGNAL,
		WRITE_SIGNAL,
		DONE
	}

	private final int phase, inputSignal;
	private State state = State.READ_PHASE;
	private int outputSignal;

	public AmplifierIo(int phase, int inputSignal) {
		this.phase = phase;
		this.inputSignal = inputSignal;
	}

	@Override
	public int read() {
		switch (state) {
		case READ_PHASE:
			state = State.READ_SIGNAL;
			return phase;
		case READ_SIGNAL:
			state = State.WRITE_SIGNAL;
			return inputSignal;
		}

		throw new IllegalStateException();
	}

	@Override
	public void write(int value) {
		Preconditions.checkState(state == State.WRITE_SIGNAL);
		outputSignal = value;
		state = State.DONE;
	}

	public int getOutputSignal() {
		Preconditions.checkState(state == State.DONE);
		return outputSignal;
	}
}
