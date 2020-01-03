package com.grahamedgecombe.advent2019.day23;

import java.util.OptionalLong;

import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class NetworkIo implements IntcodeIo {
	private enum State {
		ADDRESS,
		X,
		Y
	}

	private final Network network;
	private final int address;
	private State readState = State.ADDRESS;
	private State writeState = State.ADDRESS;
	private Packet packet;
	private int destAddress;
	private long x;

	public NetworkIo(Network network, int address) {
		this.network = network;
		this.address = address;
	}

	@Override
	public OptionalLong read() {
		if (network.isDone()) {
			return OptionalLong.empty();
		}

		switch (readState) {
		case ADDRESS:
			readState = State.X;
			return OptionalLong.of(address);
		case X:
			packet = network.pop(address);
			if (packet == null) {
				return OptionalLong.of(-1);
			}
			readState = State.Y;
			return OptionalLong.of(packet.getX());
		case Y:
			readState = State.X;
			return OptionalLong.of(packet.getY());
		}

		throw new IllegalStateException();
	}

	@Override
	public boolean write(long value) {
		if (network.isDone()) {
			return true;
		}

		switch (writeState) {
		case ADDRESS:
			destAddress = (int) value;
			writeState = State.X;
			return false;
		case X:
			x = value;
			writeState = State.Y;
			return false;
		case Y:
			network.push(destAddress, new Packet(x, value));
			writeState = State.ADDRESS;
			return false;
		default:
			throw new IllegalStateException();
		}
	}
}
