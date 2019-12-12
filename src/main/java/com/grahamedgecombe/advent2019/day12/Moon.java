package com.grahamedgecombe.advent2019.day12;

import com.google.common.base.MoreObjects;
import com.grahamedgecombe.advent2019.Vector3;

public final class Moon {
	private Vector3 position;
	private Vector3 velocity;

	public Moon(Vector3 position) {
		this.position = position;
		this.velocity = Vector3.ORIGIN;
	}

	public Vector3 getPosition() {
		return position;
	}

	public Vector3 getVelocity() {
		return velocity;
	}

	public void gravityTick(Moon other) {
		int x = Integer.signum(other.position.getX() - position.getX());
		int y = Integer.signum(other.position.getY() - position.getY());
		int z = Integer.signum(other.position.getZ() - position.getZ());
		velocity = velocity.add(x, y, z);
	}

	public void velocityTick() {
		position = position.add(velocity);
	}

	public int getEnergy() {
		return position.getEnergy() * velocity.getEnergy();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("position", position)
			.add("velocity", velocity)
			.toString();
	}
}
