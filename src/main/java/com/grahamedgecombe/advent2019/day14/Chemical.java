package com.grahamedgecombe.advent2019.day14;

import com.google.common.base.Preconditions;

public final class Chemical {
	public static Chemical parse(String str) {
		var parts = str.split(" ");
		Preconditions.checkArgument(parts.length == 2);

		var name = parts[1];
		var quantity = Integer.parseInt(parts[0]);

		return new Chemical(name, quantity);
	}

	private final String name;
	private final int quantity;

	public Chemical(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return quantity + " " + name;
	}
}
