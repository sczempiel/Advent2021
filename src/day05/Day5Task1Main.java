package day05;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.Tuple;

public class Day5Task1Main {

	public static void main(String[] args) {
		try {
			List<String> startValues = AdventUtils.getStringInput(5);

			Map<Tuple<Integer, Integer>, Long> map = startValues.stream().map(l -> {
				String[] split = l.split(",|( -> )");

				Tuple<Integer, Integer> start = Tuple.of(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
				Tuple<Integer, Integer> end = Tuple.of(Integer.valueOf(split[2]), Integer.valueOf(split[3]));

				return Tuple.of(start, end);
			}).filter(l -> Objects.equals(l.getLeft().getLeft(), l.getRight().getLeft())
					|| Objects.equals(l.getLeft().getRight(), l.getRight().getRight())).flatMap(l -> {

						List<Tuple<Integer, Integer>> points = new ArrayList<>();

						int startX = 0;
						int endX = 0;

						if (l.getLeft().getLeft() > l.getRight().getLeft()) {
							startX = l.getRight().getLeft();
							endX = l.getLeft().getLeft();
						} else {
							endX = l.getRight().getLeft();
							startX = l.getLeft().getLeft();
						}

						int startY = 0;
						int endY = 0;

						if (l.getLeft().getRight() > l.getRight().getRight()) {
							startY = l.getRight().getRight();
							endY = l.getLeft().getRight();
						} else {
							endY = l.getRight().getRight();
							startY = l.getLeft().getRight();
						}

						for (int x = startX; x <= endX; x++) {
							for (int y = startY; y <= endY; y++) {
								points.add(Tuple.of(y, x));
							}
						}

						return points.stream();
					}).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

			AdventUtils.writeExtra(5, 1, AdventUtils.printMap(map, l -> l != null ? String.valueOf(l) : "."), "grid");

			long overlapCount = map.entrySet().stream().filter(p -> p.getValue() >= 2).count();

			AdventUtils.publishResult(5, 1, overlapCount);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
