package com.grahamedgecombe.advent2019.day10;

import java.util.Objects;

import com.google.common.math.IntMath;

public final class Tangent implements Comparable<Tangent> {
	private final int opposite, adjacent;

	public Tangent(int opposite, int adjacent) {
		this.opposite = opposite;
		this.adjacent = adjacent;
	}

	public Tangent normalize() {
		var gcd = IntMath.gcd(Math.abs(opposite), Math.abs(adjacent));
		return new Tangent(opposite / gcd, adjacent / gcd);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Tangent tangent = (Tangent) o;
		return opposite == tangent.opposite &&
			adjacent == tangent.adjacent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(opposite, adjacent);
	}

	public int getQuadrant() {
		if (opposite <= 0 && adjacent >= 0) {
			return 1; /* top right */
		} else if (opposite > 0 && adjacent >= 0) {
			return 2; /* bottom right */
		} else if (opposite > 0 && adjacent < 0) {
			return 3; /* bottom left */
		} else {
			return 4; /* top left */
		}
	}

	@Override
	public int compareTo(Tangent other) {
		var quadrant = getQuadrant();
		var otherQuadrant = other.getQuadrant();
		if (quadrant != otherQuadrant) {
			return quadrant - otherQuadrant;
		}

		var opposite = this.opposite * Math.abs(other.adjacent);
		var otherOpposite = other.opposite * Math.abs(this.adjacent);
		if (quadrant == 1) {
			return opposite - otherOpposite;
		} else {
			return otherOpposite - opposite;
		}
	}
}
