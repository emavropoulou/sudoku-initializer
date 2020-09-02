package game.sudoku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages= {"game.sudoku.controllers","game.sudoku.services","game.sudoku.services.impl"})
public class SudokuApplication {

	public static void main(String[] args) {
		
		
		SpringApplication.run(SudokuApplication.class, args);
		
	}

}
