package game.sudoku.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import game.sudoku.services.InitializeNumbersService;

@Service
public class InitializeNumbersServiceImpl implements InitializeNumbersService {

	public int[][] initializeSudokuNumbers(int squareRoot) {
		int counter;

		int secondCounter = 0;

		int squared = (int) Math.pow(squareRoot, 2);

		int[][] fullSudokuTable = new int[squared][squared];

		for (int row = 0; row < (squared); row++) {
			counter = 0;

			List<Integer> availableNumbersList = fillList(squareRoot);
			List<Integer> testedNumbersList = new ArrayList<>();

			for (int column = 0; column < (squared); column++) {
				testedNumbersList.clear();

				boolean availableNumberFound = false;
				while (!availableNumberFound) {

					int numberToPut = getRandom(availableNumbersList);

					fullSudokuTable[row][column] = numberToPut;

					availableNumbersList.remove(Integer.valueOf(numberToPut));

					if (isAvailableInRow(row, column, fullSudokuTable)
							&& isAvailableInColumn(row, column, fullSudokuTable)
							&& isAvailableInBlock(row, column, squareRoot, fullSudokuTable)) {
						availableNumberFound = true;
					} else {
						availableNumberFound = false;

						availableNumbersList.add(numberToPut);

						if (!testedNumbersList.contains(numberToPut)) {
							testedNumbersList.add(numberToPut);
						}

						Collections.sort(testedNumbersList);
						Collections.sort(availableNumbersList);

						if (availableNumbersList.equals(testedNumbersList)) {
							counter = counter + 1;
							if (counter == (squared)) {
								counter = 0;
								secondCounter = secondCounter + 1;

								testedNumbersList.clear();

								availableNumbersList = fillList(squareRoot);

								if (secondCounter < row) {
									for (int tempRow = row - secondCounter; tempRow < (squared); tempRow++) {
										for (int tempColumn = 0; tempColumn < squared; tempColumn++) {
											fullSudokuTable[tempRow][tempColumn] = 0;
										}
									}
									if (row != 0) {
										column = 0;
										row = row - secondCounter;
									} else {
										row = column = 0;
									}
								} else {
									for (int tempRow = 0; tempRow < (squared); tempRow++) {
										for (int tempColumn = 0; tempColumn < squared; tempColumn++) {
											fullSudokuTable[tempRow][tempColumn] = 0;
										}
									}
									secondCounter = counter = row = column = 0;
								}
							} else {
								if (column != 0) {
									testedNumbersList.clear();
									availableNumbersList = fillList(squareRoot);
									for (int tempColumn = 0; tempColumn < squared; tempColumn++) {
										fullSudokuTable[row][tempColumn] = 0;
									}
									column = 0;
								} else {
									testedNumbersList.clear();
									availableNumbersList = fillList(squareRoot);
									for (int tempColumn = 0; tempColumn < squared; tempColumn++) {
										fullSudokuTable[row - 1][tempColumn] = 0;
									}
									if (row != 0) {
										column = 0;
										row = row - 1;
									} else {
										row = column = 0;
									}
								}
							}
						}
					}

				}
			}
		}
		return fullSudokuTable;
	}

	private List<Integer> fillList(int squareRoot) {
		List<Integer> tempList = new ArrayList<>();
		for (int i = 1; i <= squareRoot * squareRoot; i++) {
			tempList.add(i);
		}
		return tempList;
	}

	private static int getRandom(List<Integer> array) {
		int rnd = new Random().nextInt(array.size());
		return Integer.parseInt(array.get(rnd).toString());
	}

	private boolean isAvailableInRow(int currentRow, int currentColumn, int[][] data) {
		for (int j = 0; j < currentColumn; j++) {
			if (data[currentRow][j] == data[currentRow][currentColumn])
				return false;
		}
		return true;
	}

	private boolean isAvailableInColumn(int currentRow, int currentColumn, int[][] data) {
		for (int i = 0; i < currentRow; i++) {
			if (data[i][currentColumn] == data[currentRow][currentColumn])
				return false;
		}
		return true;
	}

	private boolean isAvailableInBlock(int row, int column, int squareRoot, int[][] data) {
		int block = findSquare(row, column, squareRoot);
		int rowStartBlock = (block / squareRoot) * squareRoot;
		int rowEndBlock = rowStartBlock + squareRoot;
		int columnStartBlock = (block % squareRoot) * squareRoot;
		int columnEndBlock = columnStartBlock + squareRoot;
		for (int r = rowStartBlock; r < rowEndBlock; r++) {
			for (int c = columnStartBlock; c < columnEndBlock; c++) {
				if (r != row && c != column) {
					if (data[r][c] == data[row][column])
						return false;
				}
			}
		}
		return true;
	}

	private int findSquare(int row, int column, int squareRoot) {
		return (((row / squareRoot) * squareRoot) + (column / squareRoot));
	}

}
