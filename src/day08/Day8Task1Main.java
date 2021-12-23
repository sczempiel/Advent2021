package day08;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.AdventUtils;

public class Day8Task1Main {

	public static void main(String[] args) {
		try {
			List<String> lines = AdventUtils.getStringInput(8);

			long total = 0;

			for (String line : lines) {

				String[] splitted = line.split(" \\| ");

				List<String> inputs = Arrays.asList(splitted[0].split(" "));

				Set<Character> one = findNumber(inputs, s -> s.length() == 2);
				Set<Character> four = findNumber(inputs, s -> s.length() == 4);
				Set<Character> seven = findNumber(inputs, s -> s.length() == 3);
				Set<Character> eight = findNumber(inputs, s -> s.length() == 7);

				Set<Character> three = findNumber(inputs, s -> {
					if (s.length() != 5) {
						return false;
					}

					return toSet(s).containsAll(seven);
				});

				char topLeft = four.stream().filter(c -> !three.contains(c)).findFirst().get();
				char middle = four.stream().filter(c -> !seven.contains(c)).filter(c -> c != topLeft).findFirst().get();
				char bottom = three.stream().filter(c -> !seven.contains(c)).filter(c -> c != middle).findFirst().get();

				Set<Character> zero = findNumber(inputs, s -> s.length() == 6, s -> !toSet(s).contains(middle));

				char bottomLeft = zero.stream().filter(c -> !seven.contains(c)).filter(c -> c != topLeft)
						.filter(c -> c != bottom).findFirst().get();

				Set<Character> six = findNumber(inputs, s -> s.length() == 6, s -> !toSet(s).containsAll(one));

				char topRight = one.stream().filter(c -> !six.contains(c)).findFirst().get();

				Set<Character> two = findNumber(inputs, s -> s.length() == 5, s -> !toSet(s).containsAll(three),
						s -> toSet(s).contains(topRight));
				Set<Character> five = findNumber(inputs, s -> s.length() == 5, s -> toSet(s).contains(topLeft));
				Set<Character> nine = findNumber(inputs, s -> s.length() == 6, s -> !toSet(s).contains(bottomLeft));

				int number = Integer.valueOf(Arrays.stream(splitted[1].split(" ")).map(s -> {
					Set<Character> chars = toSet(s);

					if (chars.equals(zero)) {
						return "0";
					} else if (chars.equals(one)) {
						return "1";
					} else if (chars.equals(two)) {
						return "2";
					} else if (chars.equals(three)) {
						return "3";
					} else if (chars.equals(four)) {
						return "4";
					} else if (chars.equals(five)) {
						return "5";
					} else if (chars.equals(six)) {
						return "6";
					} else if (chars.equals(seven)) {
						return "7";
					} else if (chars.equals(eight)) {
						return "8";
					} else if (chars.equals(nine)) {
						return "9";
					}
					throw new IllegalStateException();

				}).collect(Collectors.joining()));

				total += number;

			}

			AdventUtils.publishResult(8, 1, total);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SafeVarargs
	private static Set<Character> findNumber(List<String> inputs, Predicate<String>... filters) {
		Stream<String> stream = inputs.stream();

		for (Predicate<String> filter : filters) {
			stream = stream.filter(filter);
		}

		return toSet(stream.findFirst().get());
	}

	private static Set<Character> toSet(String string) {
		Set<Character> set = new HashSet<>();

		for (char c : string.toCharArray()) {
			set.add(c);
		}

		return set;
	}

}
