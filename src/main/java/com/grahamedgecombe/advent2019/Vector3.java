package com.grahamedgecombe.advent2019;

import java.util.Objects;

public final class Vector3 {
	public static final Vector3 ORIGIN = new Vector3(0, 0, 0);

	private final int x, y, z;

	public Vector3(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public Vector3 add(Vector3 other) {
		return add(other.x, other.y, other.z);
	}

	public Vector3 add(int dx, int dy, int dz) {
		return new Vector3(x + dx, y + dy, z + dz);
	}

	/* for day 12 */
	public int getEnergy() {
		return Math.abs(x) + Math.abs(y) + Math.abs(z);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Vector3 vector3 = (Vector3) o;
		return x == vector3.x &&
			y == vector3.y &&
			z == vector3.z;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}
}
