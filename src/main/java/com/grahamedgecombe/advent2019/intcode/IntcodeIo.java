package com.grahamedgecombe.advent2019.intcode;

public interface IntcodeIo {
	IntcodeIo UNSUPPORTED = new IntcodeIo() {
		@Override
		public long read() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void write(long value) {
			throw new UnsupportedOperationException();
		}
	};

	long read();
	void write(long value);
}
