package day07;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import util.AdventUtils;

public class Day7Task2Main {

	public static void main(String[] args) {
		try {
			List<Integer> crabs = Arrays.stream(AdventUtils.getStringInput(7).get(0).split(",")).map(Integer::valueOf)
					.collect(Collectors.toList());

			int min = crabs.stream().mapToInt(Integer::intValue).min().getAsInt();
			int max = crabs.stream().mapToInt(Integer::intValue).max().getAsInt();

			int minFuel = Integer.MAX_VALUE;

			for (int i = min; i <= max; i++) {

				final int position = i;

				int fuel = crabs.stream().flatMapToInt(c -> IntStream.range(1, Math.abs(position - c) + 1)).sum();

				if (fuel < minFuel) {
					minFuel = fuel;
				}
			}

			AdventUtils.publishResult(7, 2, minFuel);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
