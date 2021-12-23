package day06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;

public class Day6Task1Main {

	public static void main(String[] args) {
		try {
			List<Integer> fishes = Arrays.stream(AdventUtils.getStringInput(6).get(0).split(",")).map(Integer::valueOf)
					.collect(Collectors.toList());

			for (int i = 0; i < 80; i++) {

				List<Integer> nextFishes = new ArrayList<>();

				for (Integer fish : fishes) {
					if (fish == 0) {
						nextFishes.add(6);
						nextFishes.add(8);
					} else {
						nextFishes.add(--fish);
					}

				}

				fishes = nextFishes;
			}

			AdventUtils.publishResult(6, 1, fishes.size());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
