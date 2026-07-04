package com.IntBuddy.IntBuddy.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataisEmptyException.class)
	public ResponseEntity<ErrorResponse> detailsEmpty(DataisEmptyException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(ex.getMessage());
		error.setStatuscode(404);
		error.setTrace(ex.getStackTrace()[0].toString());
		error.setTime(LocalDateTime.now());

		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);

	}

}
