package com.grahamedgecombe.advent2019.day23;

import java.util.List;
import java.util.OptionalLong;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Network {
	private static Queue<Packet>[] createQueues(int size) {
		@SuppressWarnings("unchecked")
		var queues = (Queue<Packet>[]) new Queue[size];
		for (int i = 0; i < queues.length; i++) {
			queues[i] = new ConcurrentLinkedQueue<>();
		}
		return queues;
	}

	private final Queue<Packet>[] queues;
	private OptionalLong result = OptionalLong.empty();

	public Network(int size) {
		this.queues = createQueues(size);
	}

	public boolean isDone() {
		return result.isPresent();
	}

	public Packet pop(int address) {
		return queues[address].poll();
	}

	public void push(int address, Packet packet) {
		if (address == 255) {
			result = OptionalLong.of(packet.getY());
		} else {
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
