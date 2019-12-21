package com.grahamedgecombe.advent2019.intcode;

import java.io.IOException;
import java.io.UncheckedIOException;

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

	IntcodeIo INTERACTIVE = new IntcodeIo() {
		@Override
		public long read() {
			try {
				return System.in.read();
			} catch (IOException ex) {
				throw new UncheckedIOException(ex);
			}
		}

		@Override
		public boolean write(long value) {
			System.out.write((char) value);
			return false;
		}
	};

	long read();
	boolean write(long value);
}
