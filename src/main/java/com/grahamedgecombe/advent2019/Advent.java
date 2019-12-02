package com.grahamedgecombe.advent2019;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import com.google.common.io.Resources;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

public final class Advent {
	public static void main(String[] args) throws Exception {
		try (ScanResult result = new ClassGraph().whitelistPackages(Advent.class.getPackageName()).scan()) {
			ClassInfoList classes = result.getSubclasses(Day.class.getName());
			classes.sort(Comparator.comparingInt(Advent::getNumber));

			for (ClassInfo clazz : classes) {
				@SuppressWarnings("unchecked")
				Day<Object> day = (Day<Object>) clazz.loadClass().getDeclaredConstructor().newInstance();
				System.out.println("Day " + day.getNumber());

				Object input = parse(day);
				System.out.println("  Part 1: " + day.solvePart1(input));

				/* skip part 2 during part 1 development (and probably on day 25) */
				Object part2Result = day.solvePart2(input);
				if (part2Result != null) {
					System.out.println("  Part 2: " + part2Result);
				}
			}
		}
	}

	private static int getNumber(ClassInfo clazz) {
		int index = Day.class.getSimpleName().length();
		return Integer.parseInt(clazz.getSimpleName().substring(index));
	}

	private static <T> T parse(Day<T> day) throws IOException {
		Path path;
		try {
			path = Path.of(Resources.getResource("day" + day.getNumber() + ".txt").toURI());
		} catch (URISyntaxException ex) {
			throw new IOException(ex);
		}
		return day.parse(Files.readAllLines(path));
	}

	public static <T> Object solvePart1(Day<T> day) throws IOException {
		T input = parse(day);
		return day.solvePart1(input);
	}

	public static <T> Object solvePart2(Day<T> day) throws IOException {
		T input = parse(day);
		return day.solvePart2(input);
	}
}
