package game.sudoku.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import game.sudoku.SudokuApiEndpoints;
import game.sudoku.services.InitializeNumbersService;

@RestController
public class NumbersRestController {

	@Autowired
	InitializeNumbersService numbersService;
	
	@RequestMapping(value = SudokuApiEndpoints.GET_NUMBERS_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int[][] getInitializedParameters(@RequestParam(required = true) int root ) {

		if (root<2) {
			root = 2;
		}
		return numbersService.initializeSudokuNumbers(root);

	}
}
