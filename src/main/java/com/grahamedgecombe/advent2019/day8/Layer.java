package com.grahamedgecombe.advent2019.day8;

public final class Layer {
	public static Layer parse(int width, int height, String digits) {
		return new Layer(width, height, digits.chars().map(c -> c - '0').toArray());
	}

	private final int width, height;
	private final int[] pixels;

	private Layer(int width, int height, int[] pixels) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	public int countDigits(int digit) {
		var count = 0;

		for (int pixel : pixels) {
			if (pixel == digit) {
				count++;
			}
		}

		return count;
	}
}
