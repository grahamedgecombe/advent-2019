package com.grahamedgecombe.advent2019.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.grahamedgecombe.advent2019.Bfs;
import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.Direction;
import com.grahamedgecombe.advent2019.Vector2;
import com.grahamedgecombe.advent2019.intcode.IntcodeIo;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day15 extends Day<List<Long>> {
	private static final class Node extends Bfs.Node<Node> {
		private final IntcodeMachine machine;
		private final Vector2 position;
		private final boolean oxygen;

		public Node(IntcodeMachine machine, Vector2 position, boolean oxygen) {
			this.machine = machine;
			this.position = position;
			this.oxygen = oxygen;
		}

		@Override
		public boolean isGoal() {
			return oxygen;
		}

		@Override
		public Iterable<Node> getNeighbours() {
			var neighbours = new ArrayList<Node>();

			for (var direction : Direction.values()) {
				var io = new RepairDroidIo(direction.getDay15Command());

				var fork = machine.fork(io);
				fork.evaluate();

				var oxygen = false;
				switch (io.getStatus()) {
				case RepairDroidIo.STATUS_WALL:
					continue;
				case RepairDroidIo.STATUS_OXYGEN:
					oxygen = true;
					/* fall through */
				case RepairDroidIo.STATUS_CLEAR:
					neighbours.add(new Node(fork, position.add(direction), oxygen));
					break;
				default:
					throw new IllegalStateException();
				}
			}

			return neighbours;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Node node = (Node) o;
			return position.equals(node.position);
		}

		@Override
		public int hashCode() {
			return Objects.hash(position);
		}
	}

	public Day15() {
		super(15);
	}

	@Override
	public List<Long> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Long> input) {
		var machine = new IntcodeMachine(input, IntcodeIo.UNSUPPORTED);
		var path = Bfs.search(new Node(machine, Vector2.ORIGIN, false)).orElseThrow();
		return path.size() - 1; /* subtract one to get number of edges */
	}
}
