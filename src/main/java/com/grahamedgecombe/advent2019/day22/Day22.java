package com.grahamedgecombe.advent2019.day22;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.grahamedgecombe.advent2019.Day;

public final class Day22 extends Day<List<Technique>> {
	public static List<Integer> createDeck(int n) {
		var deck = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			deck.add(i);
		}
		return deck;
	}

	public Day22() {
		super(22);
	}

	@Override
	public List<Technique> parse(List<String> lines) {
		return Technique.parseAll(lines);
	}

	@Override
	public Object solvePart1(List<Technique> input) {
		return Technique.runAll(createDeck(10007), input).indexOf(2019);
	}

	@Override
	public Object solvePart2(List<Technique> input) {
		// Each technique can be represented as f(x) = (a * x + b) % m,
		// where:
		//
		//   x is the input position
		//   m is the size of the deck
		//   f(x) is the output position
		//   a and b are technique-specific constants
		//
		// Two techniques f(x) and f'(x) can be combined by:
		//
		//   f'(f(x)) = (a' * f(x)        +  b')          % m
		//            = (a' * (a * x + b) +  b')          % m
		//            = (a' * a * x       +  a' * b + b') % m
		//            = (a' * a * x) % m  + (a' * b + b') % m
		//
		// We can effectively treat f'(f(x)) as a single technique f''(x),
		// where:
		//
		//   a'' = (a' * a)      % m
		//   b'' = (a' * b + b') % m
		//
		// The code below combines all techniques into a single technique. We
		// can start with a = 1 and b = 0 as this represents the identity
		// function.
		var m = BigInteger.valueOf(119315717514047L);

		var a = BigInteger.ONE;
		var b = BigInteger.ZERO;
		for (var technique : input) {
			var techniqueA = BigInteger.valueOf(technique.getA());
			var techniqueB = BigInteger.valueOf(technique.getB());

			a = techniqueA.multiply(a).mod(m);
			b = techniqueA.multiply(b).add(techniqueB).mod(m);
		}

		// We re-arrange our combined f(x) to get x in terms of f(x), so we can
		// run the function in reverse:
		//
		//   x = (f(x) - b) * a^(-1)        (mod m)
		//   x = f(x) * a^(-1) - b * a^(-1) (mod m)
		//
		// Call this new function g(x). Note that g(x) is the same as f(x), but
		// with:
		//
		//   a' =      a^(-1) (mod m)
		//   b' = -b * a^(-1) (mod m)
		//
		// We adjust our a/b below for use with g(x).
		a = a.modInverse(m);
		b = b.negate().multiply(a).mod(m);

		// We need to run g(x) several times.
		//
		// g^1(x) = a * x           + b
		//
		// g^2(x) = a * g^1(x)      + b
		//        = a * (a * x + b) + b
		//        = a^2 * x + a * b + b
		//
		// g^3(x) = a^3 * x + a^2 * b + a * b + b
		// ...
		// g^n(x) = a^n * x + a^(n-1) * b + a^(n-2) * b + ... + b
		//
		// (mod m omitted for brevity.)
		//
		// The latter terms are a geometric series with first term b and common
		// ratio a.
		//
		// We can therefore simplify it to:
		//
		// g^n(x) = a^n * x + (1 - a^n) * (1 - a)^-1 * b (mod m)
		var n = BigInteger.valueOf(101741582076661L);
		var x = BigInteger.valueOf(2020L);

		// Java really needs operator overloading...
		var aToN = a.modPow(n, m);
		var aToNTimesX = aToN.multiply(x);

		var geometricSum = BigInteger.ONE.subtract(aToN)
			.multiply(BigInteger.ONE.subtract(a).modInverse(m))
			.multiply(b);

		return aToNTimesX.add(geometricSum).mod(m).longValue();
	}
}
