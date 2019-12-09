package com.grahamedgecombe.advent2019.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.google.common.collect.Collections2;
import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day7 extends Day<List<Long>> {
	public static long getMaxSignal(List<Long> program, boolean feedback) {
		var digits = feedback ? List.of(5L, 6L, 7L, 8L, 9L) : List.of(0L, 1L, 2L, 3L, 4L);
		return Collections2.permutations(digits).stream()
			.mapToLong(phases -> getSignal(program, phases, feedback))
			.max()
			.orElseThrow();
	}

	private static long getSignal(List<Long> program, List<Long> phases, boolean feedback) {
		var queues = new ArrayList<BlockingQueue<Long>>();
		for (int i = 0; i < phases.size() + 1; i++) {
			var queue = new ArrayBlockingQueue<Long>(2);
			if (i < phases.size()) {
				queue.offer(phases.get(i));
			}
			if (i == 0) {
				queue.offer(0L);
			}
			queues.add(queue);
		}

		var threads = new Thread[phases.size()];
		for (int i = 0; i < threads.length; i++) {
			var input = queues.get(i);
			var output = queues.get(feedback ? (i + 1) % threads.length : i + 1);
			var machine = new IntcodeMachine(program, new AmplifierIo(input, output));

			var thread = new Thread(machine::evaluate);
			thread.start();

			threads[i] = thread;
		}

		try {
			for (var thread : threads) {
				thread.join();
			}

			return queues.get(feedback ? 0 : phases.size()).take();
		} catch (InterruptedException ex) {
			/* we never call interrupt() */
			throw new IllegalStateException();
		}
	}

	public Day7() {
		super(7);
	}

	@Override
	public List<Long> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Long> input) {
		return getMaxSignal(input, false);
	}

	@Override
	public Object solvePart2(List<Long> input) {
		return getMaxSignal(input, true);
	}
}
