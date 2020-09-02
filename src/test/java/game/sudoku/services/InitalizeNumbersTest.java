package game.sudoku.services;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
class InitalizeNumbersTest {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	InitializeNumbersService initNumbersService;
	
	@Test
	void init() {
		
		int[][] test = initNumbersService.initializeSudokuNumbers(4);
		
		for (int i = 0; i<test.length; i++) {
			System.out.println();
			for (int j = 0; j<test.length; j++) {
				System.out.print(test[i][j]+" ");
			}
		}
		
		assertTrue(true);
	}
	

}

