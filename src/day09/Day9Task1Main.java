package day09;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import util.AdventUtils;
import util.Tuple;
import util.grid.map.GridUtils;

public class Day9Task1Main {

	public static void main(String[] args) {
		try {
			List<String> lines = AdventUtils.getStringInput(9);

			Map<Tuple<Integer, Integer>, Integer> grid = GridUtils.toGrid(lines,
					c -> Integer.valueOf(String.valueOf(c)));

			int total = 0;

			for (Tuple<Integer, Integer> point : grid.keySet()) {
				if (GridUtils.matchSurrounding(point, grid, (pV, cV) -> cV == null || pV < cV)) {
					total += (1 + grid.get(point));
				}
			}

			AdventUtils.publishResult(9, 1, total);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
