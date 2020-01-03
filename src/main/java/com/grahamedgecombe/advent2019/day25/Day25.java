package com.grahamedgecombe.advent2019.day25;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.grahamedgecombe.advent2019.Day;
import com.grahamedgecombe.advent2019.intcode.IntcodeMachine;

public final class Day25 extends Day<List<Long>> {
	private static final Pattern PASSWORD_PATTERN = Pattern.compile("typing ([0-9]+) on the keypad");

	private static Set<Set<String>> combinations(Set<String> items) {
		var combinations = new HashSet<Set<String>>();
		for (int i = 0; i < items.size(); i++) {
			combinations.addAll(Sets.combinations(items, i));
		}
		return combinations;
	}

	public Day25() {
		super(25);
	}

	@Override
	public List<Long> parse(List<String> lines) {
		return IntcodeMachine.parseProgram(lines.get(0));
	}

	@Override
	public Object solvePart1(List<Long> input) {
		// pick up all items that aren't dangerous, go to the security door,
		// and drop them all ready to try out different combinations by forking
		// the intcode machine
		var machine = new IntcodeMachine(input, new DroidIntcodeIo(List.of(
			"east",
			"take klein bottle",
			"east",
			"take semiconductor",
			"west",
			"north",
			"north",
			"north",
			"take dehydrated water",
			"south",
			"south",
			"south",
			"west",
			"north",
			"take sand",
			"north",
			"north",
			"take astrolabe",
			"south",
			"south",
			"west",
			"west",
			"take mutex",
			"east",
			"east",
			"south",
			"west",
			"north",
			"take shell",
			"south",
			"south",
			"west",
			"take ornament",
			"west",
			"south",
			"drop klein bottle",
			"drop semiconductor",
			"drop dehydrated water",
			"drop sand",
			"drop astrolabe",
			"drop mutex",
			"drop shell",
			"drop ornament"
		)));
		machine.evaluate();

		var items = Set.of(
			"klein bottle",
			"semiconductor",
			"dehydrated water",
			"sand",
			"astrolabe",
			"mutex",
			"shell",
			"ornament"
		);
		for (var combination : combinations(items)) {
			var commands = ImmutableList.<String>builder();
			for (var item : combination) {
				commands.add("take " + item);
			}
			commands.add("south");

			var io = new DroidIntcodeIo(commands.build());
			machine.fork(io).evaluate();

			var result = PASSWORD_PATTERN.matcher(io.getOutput());
			if (result.find()) {
				return result.group(1);
			}
		}

		throw new IllegalArgumentException();
	}
}
