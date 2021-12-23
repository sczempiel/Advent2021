package day08;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import util.AdventUtils;

public class Day8Task2Main {

	public static void main(String[] args) {
		try {
			List<String> lines = AdventUtils.getStringInput(8);

			long totalMatches = 0;

			for (String line : lines) {
				totalMatches += Arrays.asList(line.split(" \\| ")[1].split(" ")).stream().map(String::length)
						.filter(l -> l == 7 || l <= 4).count();

			}

			AdventUtils.publishResult(8, 2, totalMatches);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
