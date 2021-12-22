package day01;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;

public class Day1Task2Main {

	public static void main(String[] args) {
		try {
			List<Integer> startValues = AdventUtils.getIntegerInput(1);

			int larger = 0;

			int previous = startValues.stream().limit(3).mapToInt(Integer::intValue).sum();
			for (int i = 3; i < startValues.size(); i++) {

				int current1 = startValues.get(i - 2);
				int current2 = startValues.get(i - 1);
				int current3 = startValues.get(i);

				int sum = current1 + current2 + current3;

				if (sum > previous) {
					larger++;
				}

				previous = sum;
			}

			AdventUtils.publishResult(1, 2, larger);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
