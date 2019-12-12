package com.grahamedgecombe.advent2019.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.grahamedgecombe.advent2019.Vector3;

public final class Planet {
	private static final Pattern MOON_PATTERN = Pattern.compile("^<x=(-?[0-9]+), y=(-?[0-9]+), z=(-?[0-9]+)>$");

	public static Planet parse(List<String> lines) {
		var moons = new ArrayList<Moon>();

		for (var line : lines) {
			var m = MOON_PATTERN.matcher(line);
			if (!m.matches()) {
				throw new IllegalArgumentException();
			}

			int x = Integer.parseInt(m.group(1));
			int y = Integer.parseInt(m.group(2));
			int z = Integer.parseInt(m.group(3));
			moons.add(new Moon(new Vector3(x, y, z)));
		}

		return new Planet(moons);
	}

	private final List<Moon> moons;

	private Planet(List<Moon> moons) {
		this.moons = moons;
	}

	public void tick() {
		for (var i = 0; i < moons.size(); i++) {
			for (var j = 0; j < moons.size(); j++) {
				if (i != j) {
					moons.get(i).gravityTick(moons.get(j));
				}
			}
		}

		moons.forEach(Moon::velocityTick);
	}

	public void tick(int steps) {
		for (int i = 0; i < steps; i++) {
			tick();
		}
	}

	public int getEnergy() {
		return moons.stream()
			.mapToInt(Moon::getEnergy)
			.sum();
	}

	@Override
	public String toString() {
		return moons.toString();
	}
}
