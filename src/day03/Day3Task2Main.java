package day03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import util.AdventUtils;

public class Day3Task2Main {

	public static void main(String[] args) {
		try {
			List<String> lines = AdventUtils.getStringInput(3);

			List<String> oxygen = new ArrayList<>(lines);

			for (int x = 0; x < lines.get(0).length(); x++) {

				final int i = x;
				long zeroCount = oxygen.stream().map(s -> s.charAt(i)).filter(s -> s.equals('0')).count();
				long oneCount = oxygen.stream().map(s -> s.charAt(i)).filter(s -> s.equals('1')).count();

				char check;
				if (zeroCount > oneCount) {
					check = '0';
				} else {
					check = '1';
				}

				for (Iterator<String> it = oxygen.iterator(); it.hasNext();) {
					if (it.next().charAt(x) != check) {
						it.remove();
					}
				}

				if (oxygen.size() == 1) {
					break;
				}
			}

			List<String> co2 = new ArrayList<>(lines);

			for (int x = 0; x < lines.get(0).length(); x++) {

				final int i = x;
				long zeroCount = co2.stream().map(s -> s.charAt(i)).filter(s -> s.equals('0')).count();
				long oneCount = co2.stream().map(s -> s.charAt(i)).filter(s -> s.equals('1')).count();

				char check;
				if (zeroCount > oneCount) {
					check = '1';
				} else {
					check = '0';
				}

				for (Iterator<String> it = co2.iterator(); it.hasNext();) {
					if (it.next().charAt(x) != check) {
						it.remove();
					}
				}

				if (co2.size() == 1) {
					break;
				}
			}

			int lifeRating = Integer.parseInt(oxygen.get(0), 2) * Integer.parseInt(co2.get(0), 2);

			AdventUtils.publishResult(3, 2, lifeRating);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
