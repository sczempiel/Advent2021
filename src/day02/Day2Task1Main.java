package day02;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.Tuple;

public class Day2Task1Main {

	public static void main(String[] args) {
		try {
			List<Tuple<String, Integer>> startValues = AdventUtils.getStringInput(2).stream().map(l -> l.split(" "))
					.map(s -> Tuple.of(s[0], Integer.valueOf(s[1]))).collect(Collectors.toList());

			int forward = 0;
			int depth = 0;

			for (Tuple<String, Integer> line : startValues) {
				if (line.getLeft().equals("forward")) {
					forward += line.getRight();
				} else if (line.getLeft().equals("up")) {
					depth -= line.getRight();
				} else {
					depth += line.getRight();
				}
			}
			AdventUtils.publishResult(2, 1, forward * depth);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
