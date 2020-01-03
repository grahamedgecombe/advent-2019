package com.grahamedgecombe.advent2019.intcode;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.OptionalLong;

public interface IntcodeIo {
	IntcodeIo UNSUPPORTED = new IntcodeIo() {
		@Override
		public OptionalLong read() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean write(long value) {
			throw new UnsupportedOperationException();
		}
	};

	IntcodeIo INTERACTIVE = new IntcodeIo() {
		@Override
		public OptionalLong read() {
			try {
				return OptionalLong.of(System.in.read());
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

	OptionalLong read();
	boolean write(long value);
}
