package com.grahamedgecombe.advent2019;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.common.io.Resources;

public final class AdventUtils {
	public static String readString(String file) throws IOException {
		try {
			var path = Paths.get(Resources.getResource(file).toURI());
			return Files.readString(path).trim();
		} catch (URISyntaxException ex) {
			throw new IOException(ex);
		}
	}

	public static List<String> readLines(String file) throws IOException {
		try {
			var path = Paths.get(Resources.getResource(file).toURI());
			return Files.readAllLines(path);
		} catch (URISyntaxException ex) {
			throw new IOException(ex);
		}
	}

	private AdventUtils() {
		/* empty */
	}
}
