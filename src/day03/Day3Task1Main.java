package day03;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;

public class Day3Task1Main {

	public static void main(String[] args) {
		try {
			List<String> lines = AdventUtils.getStringInput(3);

			String mostCommen = "";
			String leastCommen = "";

			for (int x = 0; x < lines.get(0).length(); x++) {

				int zeroCount = 0;
				int oneCount = 0;

				for (int y = 0; y < lines.size(); y++) {

					if (lines.get(y).charAt(x) == '0') {
						zeroCount++;
					} else {
						oneCount++;
					}
				}

				if (zeroCount > oneCount) {
					mostCommen += "0";
					leastCommen += "1";
				} else {
					mostCommen += "1";
					leastCommen += "0";
				}
			}

			int power = Integer.parseInt(mostCommen, 2) * Integer.parseInt(leastCommen, 2);

			AdventUtils.publishResult(3, 1, power);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
