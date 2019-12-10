package com.grahamedgecombe.advent2019.day10;

import java.util.Objects;

public final class Tangent {
	private static int gcd(int a, int b) {
		if (b == 0) {
			return Math.abs(a);
		}
		return gcd(b, a % b);
	}

	private final int opposite, adjacent;

	public Tangent(int opposite, int adjacent) {
		this.opposite = opposite;
		this.adjacent = adjacent;
	}

	public Tangent normalize() {
		var gcd = gcd(opposite, adjacent);
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
}
