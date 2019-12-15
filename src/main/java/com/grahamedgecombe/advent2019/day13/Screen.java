package com.grahamedgecombe.advent2019.day13;

import java.util.HashMap;
import java.util.Map;

import com.grahamedgecombe.advent2019.Vector2;
import com.grahamedgecombe.advent2019.intcode.IntcodeIo;

public final class Screen implements IntcodeIo {
	private static final int TILE_EMPTY = 0;
	private static final int TILE_WALL = 1;
	private static final int TILE_BLOCK = 2;
	private static final int TILE_HORIZONTAL_PADDLE = 3;
	private static final int TILE_BALL = 4;

	private enum State {
		READ_X,
		READ_Y,
		READ_TILE
	}

	private final Map<Vector2, Integer> tiles = new HashMap<>();
	private State state = State.READ_X;
	private int x, y, score;

	private Vector2 getPosition(int tile) {
		return tiles.entrySet().stream()
			.filter(e -> e.getValue() == tile)
			.map(Map.Entry::getKey)
			.findFirst()
			.orElseThrow();
	}

	@Override
	public long read() {
		var paddlePos = getPosition(TILE_HORIZONTAL_PADDLE);
		var ballPos = getPosition(TILE_BALL);
		return Integer.signum(ballPos.getX() - paddlePos.getX());
	}

	@Override
	public boolean write(long value) {
		switch (state) {
		case READ_X:
			x = (int) value;
			state = State.READ_Y;
			break;
		case READ_Y:
			y = (int) value;
			state = State.READ_TILE;
			break;
		case READ_TILE:
			if (x == -1 && y == 0) {
				score = (int) value;
			} else {
				tiles.put(new Vector2(x, y), (int) value);
			}
			state = State.READ_X;
			break;
		default:
			throw new IllegalStateException();
		}

		return false;
	}

	public long countBlockTiles() {
		return tiles.values().stream()
			.filter(tile -> tile == TILE_BLOCK)
			.count();
	}

	public int getScore() {
		return score;
	}
}
