package com.grahamedgecombe.advent2019.day7;

import java.util.OptionalLong;
import java.util.concurrent.BlockingQueue;

import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class AmplifierIo implements IntcodeIo {
	private final BlockingQueue<Long> input, output;

	public AmplifierIo(BlockingQueue<Long> input, BlockingQueue<Long> output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public OptionalLong read() {
		try {
			return OptionalLong.of(input.take());
		} catch (InterruptedException ex) {
			/* we never call interrupt() */
			throw new IllegalStateException();
		}
	}

	@Override
	public boolean write(long value) {
		try {
			output.put(value);
		} catch (InterruptedException ex) {
			/* we never call interrupt() */
			throw new IllegalStateException();
		}

		return false;
	}
}
