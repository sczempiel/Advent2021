package day01;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;

public class Day1Task1Main {

	public static void main(String[] args) {
		try {
			List<Integer> startValues = AdventUtils.getIntegerInput(1);

			int larger = 0;

			int previous = startValues.get(0);
			for (int i = 1; i < startValues.size(); i++) {

				int current = startValues.get(i);

				if (current > previous) {
					larger++;
				}

				previous = current;
			}

			AdventUtils.publishResult(1, 1, larger);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
