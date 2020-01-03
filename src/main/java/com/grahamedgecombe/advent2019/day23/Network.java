package com.grahamedgecombe.advent2019.day23;

import java.util.ArrayDeque;
import java.util.List;
import java.util.OptionalLong;
import java.util.Queue;

import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Network {
	private static Queue<Packet>[] createQueues(int size) {
		@SuppressWarnings("unchecked")
		var queues = (Queue<Packet>[]) new Queue[size];
		for (int i = 0; i < queues.length; i++) {
			queues[i] = new ArrayDeque<>();
		}
		return queues;
	}

	private final Queue<Packet>[] queues;
	private final boolean[] reading;
	private final boolean nat;
	private Packet lastNatPacket;
	private OptionalLong lastDeliveredNatY = OptionalLong.empty();
	private OptionalLong result = OptionalLong.empty();

	public Network(int size, boolean nat) {
		this.queues = createQueues(size);
		this.reading = new boolean[size];
		this.nat = nat;
	}

	public synchronized boolean isDone() {
		return result.isPresent();
	}

	private boolean isIdle() {
		// all computers must have empty queues
		for (var queue : queues) {
			if (!queue.isEmpty()) {
				return false;
			}
		}

		// all computers must be attempting to read
		for (var r : reading) {
			if (!r) {
				return false;
			}
		}

		return true;
	}

	public synchronized Packet pop(int address) {
		reading[address] = true;

		if (nat && isIdle() && address == 0) {
			lastDeliveredNatY.ifPresent(y -> {
				if (y == lastNatPacket.getY()) {
					result = OptionalLong.of(y);
				}
			});
			lastDeliveredNatY = OptionalLong.of(lastNatPacket.getY());
			return lastNatPacket;
		}

		return queues[address].poll();
	}

	public synchronized void push(int address, Packet packet) {
		if (address == 255) {
			if (nat) {
				lastNatPacket = packet;
			} else {
				result = OptionalLong.of(packet.getY());
			}
		} else {
			reading[address] = false;
			queues[address].add(packet);
		}
	}

	public long evaluate(List<Long> program) {
		var threads = new Thread[queues.length];

		for (var i = 0; i < threads.length; i++) {
			final var address = i;
			var thread = threads[i] = new Thread(() -> {
				var machine = new IntcodeMachine(program, new NetworkIo(this, address));
				machine.evaluate();
			});
			thread.start();
		}

		for (var thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException ex) {
				/* we never call interrupt() */
				throw new IllegalStateException();
			}
		}

		return result.getAsLong();
	}
}
