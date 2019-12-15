package com.grahamedgecombe.advent2019.intcode;

public interface IntcodeIo {
	IntcodeIo UNSUPPORTED = new IntcodeIo() {
		@Override
		public long read() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean write(long value) {
			throw new UnsupportedOperationException();
		}
	};

	long read();
	boolean write(long value);
}
