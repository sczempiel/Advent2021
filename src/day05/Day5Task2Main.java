package day05;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.Tuple;
import util.grid.map.GridUtils;

public class Day5Task2Main {

	public static void main(String[] args) {
		try {
			List<String> startValues = AdventUtils.getStringInput(5);

			Map<Tuple<Integer, Integer>, Long> map = startValues.stream().map(l -> {
				String[] split = l.split(",|( -> )");

				Tuple<Integer, Integer> start = Tuple.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
				Tuple<Integer, Integer> end = Tuple.of(Integer.valueOf(split[2]), Integer.valueOf(split[3]));

				return Tuple.of(start, end);
			}).flatMap(l -> convertToPoints(l).stream())
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

			AdventUtils.writeExtra(5, 2, GridUtils.print(map, l -> l != null ? String.valueOf(l) : "."), "grid");

			long overlapCount = map.entrySet().stream().filter(p -> p.getValue() >= 2).count();

			AdventUtils.publishResult(5, 2, overlapCount);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<Tuple<Integer, Integer>> convertToPoints(
			Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>> l) {
		List<Tuple<Integer, Integer>> points = new ArrayList<>();

		int startX = l.getRight().getLeft();
		int startY = l.getRight().getRight();

		int endX = l.getLeft().getLeft();
		int endY = l.getLeft().getRight();

		int stepX = 0;
		int stepY = 0;

		if (startX < endX) {
			stepX = 1;
		} else if (startX > endX) {
			stepX = -1;
		}

		if (startY < endY) {
			stepY = 1;
		} else if (startY > endY) {
			stepY = -1;
		}

		int x = startX;
		int y = startY;

		while (!(x == endX && y == endY)) {
			points.add(Tuple.of(y, x));
			x += stepX;
			y += stepY;
		}

		points.add(Tuple.of(y, x));

		return points;
	}

}
