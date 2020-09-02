package game.sudoku.controllers.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import game.sudoku.domains.ApiError;

@ControllerAdvice
public class GeneralExceptionsControllerAdvice extends ResponseEntityExceptionHandler {

	public GeneralExceptionsControllerAdvice() {
		super();
	}

	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class, NumberFormatException.class,
			IllegalArgumentException.class, JsonMappingException.class, JsonProcessingException.class,
			NullPointerException.class, UnrecognizedPropertyException.class })
	protected ResponseEntity<Object> handleBadRequest(final RuntimeException exception, final WebRequest request) {
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.toString(), exception.getClass().getSimpleName().toString(),
				exception.getMessage());

		return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.toString(), exception.getClass().getSimpleName().toString(),
				exception.getMessage());

		return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.toString(), exception.getClass().getSimpleName().toString(),
				exception.getMessage());


		return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.toString(), exception.getClass().getSimpleName().toString(),
				exception.getMessage());

		return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.toString(), exception.getClass().getSimpleName().toString(),
				exception.getMessage());


		return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
