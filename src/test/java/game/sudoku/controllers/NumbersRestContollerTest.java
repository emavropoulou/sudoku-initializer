package game.sudoku.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import game.sudoku.SudokuApiEndpoints;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class NumbersRestContollerTest {
	@Autowired
	private NumbersRestController testController;
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() throws Exception {
		assertThat(testController).isNotNull();
	}

	@Test
	public void initalizeSudoku2x2() throws Exception {
		HttpStatus httpStatus = this.restTemplate.exchange("http://localhost:" + port + SudokuApiEndpoints.GET_NUMBERS_PATH+"?root=2",
				HttpMethod.GET, new HttpEntity<>(getHeaders()), Object.class).getStatusCode();
		assertEquals(HttpStatus.OK, httpStatus);
	}

	@Test
	public void initalizeSudoku3x3() throws Exception {
		HttpStatus httpStatus = this.restTemplate.exchange("http://localhost:" + port + SudokuApiEndpoints.GET_NUMBERS_PATH+"?root=3",
				HttpMethod.GET, new HttpEntity<>(getHeaders()),Object.class).getStatusCode();
		assertEquals(HttpStatus.OK, httpStatus);
	}

	@Test
	public void initalizeSudoku4x4() throws Exception {
		HttpStatus httpStatus = this.restTemplate.exchange("http://localhost:" + port + SudokuApiEndpoints.GET_NUMBERS_PATH+"?root=4",
				HttpMethod.GET, new HttpEntity<>(getHeaders()), Object.class).getStatusCode();
		assertEquals(HttpStatus.OK, httpStatus);
	}
	
	@Test
	public void initalizeSudokuNoRootInit() throws Exception {
		HttpStatus httpStatus = this.restTemplate.exchange("http://localhost:" + port + SudokuApiEndpoints.GET_NUMBERS_PATH,
				HttpMethod.GET, new HttpEntity<>(getHeaders()), Object.class).getStatusCode();
		assertEquals(HttpStatus.BAD_REQUEST, httpStatus);
	}
	
	@Test
	public void invalidMethodTest() throws Exception {
		HttpStatus httpStatus = this.restTemplate.exchange("http://localhost:" + port + SudokuApiEndpoints.GET_NUMBERS_PATH,
				HttpMethod.POST, new HttpEntity<>(getHeaders()), Object.class).getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, httpStatus);
	}
	
	
	public static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		return headers;
	}

}
