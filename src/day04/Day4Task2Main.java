package day04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;

public class Day4Task2Main {

	private static List<int[][]> boards = new ArrayList<>();

	public static void main(String[] args) {
		try {
			List<String> startValues = new ArrayList<>(AdventUtils.getStringInput(4));

			List<Integer> draws = Arrays.asList(startValues.get(0).split(",")).stream().map(Integer::valueOf)
					.collect(Collectors.toList());

			startValues.remove(0);
			startValues.remove(0);

			fillBoards(startValues);

			int[][] loosingBoard = null;
			int lastDrawn = 0;

			for (Integer draw : draws) {
				lastDrawn = draw;

				for (int[][] board : boards) {
					for (int y = 0; y < 5; y++) {
						for (int x = 0; x < 5; x++) {
							if (board[y][x] == draw) {
								board[y][x] = -1;
							}
						}
					}
				}

				for (Iterator<int[][]> it = boards.iterator(); it.hasNext();) {

					int[][] board = it.next();

					boolean win = false;
					for (int x = 0; x < 5; x++) {
						int sum = 0;

						for (int y = 0; y < 5; y++) {
							sum += board[y][x];
						}

						if (sum == -5) {
							win = true;
						}
					}

					for (int y = 0; y < 5; y++) {
						int sum = 0;

						for (int x = 0; x < 5; x++) {
							sum += board[y][x];
						}

						if (sum == -5) {
							win = true;
						}
					}

					if (win) {
						loosingBoard = board;
						it.remove();
					}
				}

				if (boards.isEmpty()) {
					break;
				}

			}

			int sum = 0;
			for (int y = 0; y < 5; y++) {

				for (int x = 0; x < 5; x++) {
					if (loosingBoard[y][x] >= 0) {
						sum += loosingBoard[y][x];
					}
				}
			}

			AdventUtils.publishResult(4, 2, sum * lastDrawn);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void fillBoards(List<String> startValues) {
		int[][] board = new int[5][5];
		int row = 0;
		boards.add(board);

		for (String line : startValues) {

			if (line.isEmpty()) {
				board = new int[5][5];
				row = 0;
				boards.add(board);

				continue;
			}

			board[row] = Arrays.asList(line.split(" ")).stream().filter(s -> !s.trim().isEmpty())
					.mapToInt(Integer::valueOf).toArray();
			row++;
		}
	}

}
