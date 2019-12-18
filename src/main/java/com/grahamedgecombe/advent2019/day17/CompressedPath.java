package com.grahamedgecombe.advent2019.day17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public final class CompressedPath {
	private static final int MAX_LENGTH = 20;

	public static CompressedPath compress(List<String> path) {
		return compress(rle(path), 'A', new HashMap<>()).orElseThrow(IllegalArgumentException::new);
	}

	private static Optional<CompressedPath> compress(List<String> path, char function, java.util.Map<Character, List<String>> bodies) {
		/* skip over any A/B/C commands (recursive function calls not allowed) */
		var firstCommand = path.stream().filter(c -> !isCall(c)).findFirst();
		if (firstCommand.isEmpty()) {
			return Optional.empty();
		}

		var off = path.indexOf(firstCommand.get());

		/*
		 * try every possible sublist as a function body (we only need to start
		 * these at 'off' rather than every index from 'off' to 'len' as we
		 * can't leave any commands in the main function)
		 */
		for (var len = 1; len <= path.size() - off; len++) {
			var functionBody = path.subList(off, off + len);
			if (getLength(functionBody) > MAX_LENGTH || containsCall(functionBody)) {
				break;
			}

			/* replace all instances of the function body with a call */
			bodies.put(function, functionBody);
			var newPath = replaceBodyWithCall(path, functionBody, function);

			/* if the main function only contains function calls and is short enough, we're done */
			if (getLength(newPath) <= MAX_LENGTH && containsOnlyCalls(newPath)) {
				var main = String.join(",", newPath);
				var a = String.join(",", bodies.getOrDefault('A', List.of()));
				var b = String.join(",", bodies.getOrDefault('B', List.of()));
				var c = String.join(",", bodies.getOrDefault('C', List.of()));
				return Optional.of(new CompressedPath(main, a, b, c));
			}

			/* recurse if we still have free function(s) */
			if (function != 'C') {
				var result = compress(newPath, (char) (function + 1), bodies);
				if (result.isPresent()) {
					return result;
				}
			}
		}

		return Optional.empty();
	}

	private static List<String> replaceBodyWithCall(List<String> in, List<String> body, char function) {
		var out = new ArrayList<>(in);

		int index;
		while ((index = Collections.indexOfSubList(out, body)) != -1) {
			var window = out.subList(index, index + body.size());
			window.clear();
			window.add(Character.toString(function));
		}

		return out;
	}

	private static int getLength(List<String> in) {
		var commas = in.size() - 1;
		return in.stream().mapToInt(String::length).sum() + commas;
	}

	private static boolean isCall(String c) {
		return c.equals("A") || c.equals("B") || c.equals("C");
	}

	private static boolean containsCall(List<String> in) {
		return in.stream().anyMatch(CompressedPath::isCall);
	}

	private static boolean containsOnlyCalls(List<String> in) {
		return in.stream().allMatch(CompressedPath::isCall);
	}

	public static List<String> rle(List<String> in) {
		var out = new ArrayList<String>();
		var len = 0;

		for (var command : in) {
			if (command.equals("1")) {
				len++;
			} else {
				if (len > 0) {
					out.add(Integer.toString(len));
					len = 0;
				}

				out.add(command);
			}
		}

		if (len > 0) {
			out.add(Integer.toString(len));
		}

		return out;
	}

	private final String main, a, b, c;

	public CompressedPath(String main, String a, String b, String c) {
		this.main = main;
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public String getMain() {
		return main;
	}

	public String getA() {
		return a;
	}

	public String getB() {
		return b;
	}

	public String getC() {
		return c;
	}
}
