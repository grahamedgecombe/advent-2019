package com.grahamedgecombe.advent2019.day12;

import java.util.Objects;

public final class MoonAxis {
	private final int position, velocity;

	public MoonAxis(int position, int velocity) {
		this.position = position;
		this.velocity = velocity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MoonAxis moonAxis = (MoonAxis) o;
		return position == moonAxis.position &&
			velocity == moonAxis.velocity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, velocity);
	}
}
