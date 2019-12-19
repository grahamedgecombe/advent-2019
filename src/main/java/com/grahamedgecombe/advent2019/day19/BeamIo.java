package com.grahamedgecombe.advent2019.day19;

import com.google.common.base.Preconditions;
import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class BeamIo implements IntcodeIo {
	private enum State {
		READ_X,
		READ_Y,
		WRITE,
		DONE
	}

	private final int x, y;
	private State state = State.READ_X;
	private boolean affected;

	public BeamIo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public long read() {
		switch (state) {
		case READ_X:
			state = State.READ_Y;
			return x;
		case READ_Y:
			state = State.WRITE;
			return y;
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public boolean write(long value) {
		Preconditions.checkState(state == State.WRITE);
		state = State.DONE;
		affected = value == 1;
		return false;
	}

	public boolean isAffected() {
		return affected;
	}
}
