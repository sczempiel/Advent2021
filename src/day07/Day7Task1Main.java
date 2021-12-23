package day07;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;

public class Day7Task1Main {

	public static void main(String[] args) {
		try {
			List<Integer> crabs = Arrays.asList(AdventUtils.getStringInput(7).get(0).split(",")).stream()
					.map(Integer::valueOf).collect(Collectors.toList());

			int min = crabs.stream().mapToInt(Integer::intValue).min().getAsInt();
			int max = crabs.stream().mapToInt(Integer::intValue).max().getAsInt();

			int minFuel = Integer.MAX_VALUE;

			for (int i = min; i <= max; i++) {

				final int position = i;

				int fuel = crabs.stream().mapToInt(c -> Math.abs(position - c)).sum();

				if (fuel < minFuel) {
					minFuel = fuel;
				}
			}

			AdventUtils.publishResult(7, 1, minFuel);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
