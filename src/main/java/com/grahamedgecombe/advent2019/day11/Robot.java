package com.grahamedgecombe.advent2019.day11;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.grahamedgecombe.advent2019.Direction;
import com.grahamedgecombe.advent2019.Vector2;
import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class Robot implements IntcodeIo {
	public static final int BLACK = 0;
	public static final int WHITE = 1;

	private enum State {
		READ_COLOR,
		WRITE_COLOR,
		WRITE_DIRECTION
	}

	private final Map<Vector2, Integer> panels = new HashMap<>();
	private Vector2 position = Vector2.ORIGIN;
	private Direction direction = Direction.UP;
	private State state = State.READ_COLOR;

	@Override
	public long read() {
		Preconditions.checkState(state == State.READ_COLOR);
		state = State.WRITE_COLOR;
		return panels.getOrDefault(position, BLACK);
	}

	@Override
	public boolean write(long value) {
		switch (state) {
		case WRITE_COLOR:
			panels.put(position, (int) value);
			state = State.WRITE_DIRECTION;
			break;
		case WRITE_DIRECTION:
			direction = value == 0 ? direction.getLeft() : direction.getRight();
			position = position.add(direction);
			state = State.READ_COLOR;
			break;
		default:
			throw new IllegalStateException();
		}

		return false;
	}

	public void setColor(Vector2 position, int color) {
		panels.put(position, color);
	}

	public int getPaintedPanels() {
		return panels.size();
	}

	@Override
	public String toString() {
		var xStats = panels.entrySet().stream()
			.filter(e -> e.getValue() == WHITE)
			.mapToInt(e -> e.getKey().getX())
			.summaryStatistics();

		var yStats = panels.entrySet().stream()
			.filter(e -> e.getValue() == WHITE)
			.mapToInt(e -> e.getKey().getY())
			.summaryStatistics();

		var builder = new StringBuilder();
		for (var y = yStats.getMax(); y >= yStats.getMin(); y--) {
			for (var x = xStats.getMin(); x <= xStats.getMax(); x++) {
				var color = panels.getOrDefault(new Vector2(x, y), BLACK);
				builder.append(color == WHITE ? '#' : '.');
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
