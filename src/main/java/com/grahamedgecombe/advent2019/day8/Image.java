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
}
