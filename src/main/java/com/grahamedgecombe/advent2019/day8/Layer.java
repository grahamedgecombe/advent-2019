package com.grahamedgecombe.advent2019.day8;

public final class Layer {
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	public static final int TRANSPARENT = 2;

	public static Layer parse(int width, int height, String digits) {
		return new Layer(width, height, digits.chars().map(c -> c - '0').toArray());
	}

	private final int width, height;
	private final int[] pixels;

	public Layer(int width, int height, int[] pixels) {
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

	public int getPixel(int i) {
		return pixels[i];
	}

	@Override
	public String toString() {
		var builder = new StringBuilder();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				var color = pixels[y * width + x];
				builder.append(color == Layer.WHITE ? '#' : ' ');
			}
			builder.append('\n');
		}

		return builder.toString();
	}
}
