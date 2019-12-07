package com.grahamedgecombe.advent2019.day7;

import java.util.concurrent.BlockingQueue;

import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class AmplifierIo implements IntcodeIo {
	private final BlockingQueue<Integer> input, output;

	public AmplifierIo(BlockingQueue<Integer> input, BlockingQueue<Integer> output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public int read() {
		try {
			return input.take();
		} catch (InterruptedException ex) {
			/* we never call interrupt() */
			throw new IllegalStateException();
		}
	}

	@Override
	public void write(int value) {
		try {
			output.put(value);
		} catch (InterruptedException ex) {
			/* we never call interrupt() */
			throw new IllegalStateException();
		}
	}
}
