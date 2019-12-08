package com.grahamedgecombe.advent2019.day8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Image {
	public static Image parse(int width, int height, String digits) {
		var layers = new ArrayList<Layer>();
		for (var i = 0; i < digits.length(); i+= width * height) {
			layers.add(Layer.parse(width, height, digits.substring(i, i + width * height)));
		}
		return new Image(width, height, layers);
	}

	private static int combineColors(int back, int front) {
		return front == Layer.TRANSPARENT ? back : front;
	}

	private final int width, height;
	private final List<Layer> layers;

	private Image(int width, int height, List<Layer> layers) {
		this.width = width;
		this.height = height;
		this.layers = layers;
	}

	public int findFewestZeroDigits() {
		var layer = layers.stream()
			.min(Comparator.comparing(l -> l.countDigits(0)))
			.orElseThrow();
		return layer.countDigits(1) * layer.countDigits(2);
	}

	public Layer combineLayers() {
		var pixels = new int[width * height];

		for (int i = 0; i < pixels.length; i++) {
			var color = layers.get(layers.size() - 1).getPixel(i);

			for (int j = layers.size() - 2; j >= 0; j--) {
				color = combineColors(color, layers.get(j).getPixel(i));
			}

			pixels[i] = color;
		}

		return new Layer(width, height, pixels);
	}
}
