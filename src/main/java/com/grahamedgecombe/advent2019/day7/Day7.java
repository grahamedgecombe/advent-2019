package com.grahamedgecombe.advent2019.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.google.common.collect.Collections2;
import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day7 extends Day<List<Integer>> {
	public static int getMaxSignal(List<Integer> program, boolean feedback) {
		var digits = feedback ? List.of(5, 6, 7, 8, 9) : List.of(0, 1, 2, 3, 4);
		return Collections2.permutations(digits).stream()
			.mapToInt(phases -> getSignal(program, phases, feedback))
			.max()
			.orElseThrow();
	}

	private static int getSignal(List<Integer> program, List<Integer> phases, boolean feedback) {
		var queues = new ArrayList<BlockingQueue<Integer>>();
		for (int i = 0; i < phases.size() + 1; i++) {
			var queue = new ArrayBlockingQueue<Integer>(2);
			if (i < phases.size()) {
				queue.offer(phases.get(i));
			}
			if (i == 0) {
				queue.offer(0);
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
	public List<Integer> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Integer> input) {
		return getMaxSignal(input, false);
	}

	@Override
	public Object solvePart2(List<Integer> input) {
		return getMaxSignal(input, true);
	}
}
