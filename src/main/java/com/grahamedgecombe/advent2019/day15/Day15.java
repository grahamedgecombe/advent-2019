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
		private final boolean part2;

		public Node(IntcodeMachine machine, Vector2 position, boolean oxygen, boolean part2) {
			this.machine = machine;
			this.position = position;
			this.oxygen = oxygen;
			this.part2 = part2;
		}

		@Override
		public boolean isGoal() {
			return oxygen || part2;
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
					neighbours.add(new Node(fork, position.add(direction), oxygen, part2));
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

	private List<Node> getOxygenPath(List<Long> program) {
		var machine = new IntcodeMachine(program, IntcodeIo.UNSUPPORTED);
		return Bfs.search(new Node(machine, Vector2.ORIGIN, false, false)).orElseThrow();
	}

	@Override
	public Object solvePart1(List<Long> input) {
		var path = getOxygenPath(input);
		return path.size() - 1; /* subtract one to get number of edges */
	}

	@Override
	public Object solvePart2(List<Long> input) {
		/* get position of the oxygen */
		var path = getOxygenPath(input);
		var oxygen = path.get(path.size() - 1);

		/* find paths from the oxygen to all tiles */
		var paths = Bfs.searchAll(new Node(oxygen.machine, oxygen.position, oxygen.oxygen, true));

		/* the longest path determines how long it takes to fill the area with oxygen */
		return paths.stream()
			.mapToInt(p -> p.size() - 1)
			.max()
			.orElseThrow();
	}
}
