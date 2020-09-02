package game.sudoku.controllers.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import game.sudoku.domains.ApiError;

@RestController
public class ExceptionsController implements ErrorController {

	@RequestMapping(value = "/error", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ApiError error(HttpServletRequest request, HttpServletResponse res) {
		
		ApiError apiError = new ApiError();
		switch (res.getStatus()) {
		case HttpServletResponse.SC_UNAUTHORIZED:
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			apiError.setCode(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
			apiError.setTitle("UNAUTHORIZED");
			apiError.setException("You don't have access to this resource.");

			break;
		case HttpServletResponse.SC_FORBIDDEN:
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			apiError.setCode(String.valueOf(HttpServletResponse.SC_FORBIDDEN));
			apiError.setTitle("FORBIDDEN");
			apiError.setException("Access is forbidden.");
			break;
		default:
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			apiError.setCode(String.valueOf(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
			apiError.setTitle("INTERNAL SERVER ERROR");
			apiError.setException("An error occured, please try again later.");
		}
		return apiError;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
