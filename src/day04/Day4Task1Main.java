package day04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;

public class Day4Task1Main {

	private static List<int[][]> boards = new ArrayList<>();

	public static void main(String[] args) {
		try {
			List<String> startValues = new ArrayList<>(AdventUtils.getStringInput(4));

			List<Integer> draws = Arrays.asList(startValues.get(0).split(",")).stream().map(Integer::valueOf)
					.collect(Collectors.toList());

			startValues.remove(0);
			startValues.remove(0);

			fillBoards(startValues);

			int[][] winningBoard = null;
			int lastDrawn = 0;

			draws: for (Integer draw : draws) {
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

				for (int[][] board : boards) {
					for (int x = 0; x < 5; x++) {
						int sum = 0;

						for (int y = 0; y < 5; y++) {
							sum += board[y][x];
						}

						if (sum == -5) {
							winningBoard = board;
							break draws;
						}
					}

					for (int y = 0; y < 5; y++) {
						int sum = 0;

						for (int x = 0; x < 5; x++) {
							sum += board[y][x];
						}

						if (sum == -5) {
							winningBoard = board;
							break draws;
						}
					}
				}

			}

			int sum = 0;
			for (int y = 0; y < 5; y++) {

				for (int x = 0; x < 5; x++) {
					if (winningBoard[y][x] >= 0) {
						sum += winningBoard[y][x];
					}
				}
			}

			AdventUtils.publishResult(4, 1, sum * lastDrawn);

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
