package com.hexad.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hexad.response.LibraryResponse;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(value = LibraryException.class)
	public ResponseEntity<Object> libraryException(LibraryException exception) {
		return null;}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(LibraryException exception) {
		return null;}
}
