package day06;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.AdventUtils;

public class Day6Task2Main {

	public static void main(String[] args) {
		try {
			List<Integer> fishes = Arrays.stream(AdventUtils.getStringInput(6).get(0).split(",")).map(Integer::valueOf)
					.collect(Collectors.toList());

			long fishCount = 0;

			for (int i = 0; i < 256; i++) {

				System.out.println(i);

				fishes = fishes.stream().parallel().flatMap(fish -> {
					if (fish == 0) {
						return Stream.concat(Stream.of(6), Stream.of(8));
					} else {
						return Stream.of(--fish);
					}
				}).collect(Collectors.toList());
			}

			AdventUtils.publishResult(6, 2, fishes.size());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
