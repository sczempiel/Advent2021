package day11;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.AdventUtils;
import util.Tuple;
import util.grid.map.GridUtils;

public class Day11Task1Main {

	private static Map<Tuple<Integer, Integer>, Integer> grid;
	private static Set<Tuple<Integer, Integer>> flashed = new HashSet<>();

	public static void main(String[] args) {
		try {
			List<String> lines = AdventUtils.getStringInput(11);

			grid = GridUtils.toGrid(lines, c -> Integer.valueOf(String.valueOf(c)));

			int flashes = 0;

			for (int i = 0; i < 100; i++) {

				grid.keySet().forEach(p -> grid.put(p, grid.get(p) + 1));

				grid.keySet().forEach(Day11Task1Main::flashIfPossible);

				flashes += flashed.size();

				flashed.stream().forEach(p -> grid.put(p, 0));
				flashed.clear();

			}
			AdventUtils.publishResult(11, 1, flashes);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void flashIfPossible(Tuple<Integer, Integer> point) {

		Integer value = grid.get(point);

		if (value == null || value < 10 || flashed.contains(point)) {
			return;
		}

		flashed.add(point);

		GridUtils.consumeSurrounding(point, p -> {
			Integer val = grid.get(p);

			if (val == null) {
				return;
			}

			grid.put(p, val + 1);
		});

		GridUtils.consumeSurrounding(point, Day11Task1Main::flashIfPossible);
	}

}
