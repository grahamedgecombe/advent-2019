package com.grahamedgecombe.advent2019.intcode;

public interface IntcodeIo {
	IntcodeIo UNSUPPORTED = new IntcodeIo() {
		@Override
		public int read() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void write(int value) {
			throw new UnsupportedOperationException();
		}
	};

	int read();
	void write(int value);
}
