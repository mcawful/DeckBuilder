/**
 * 
 */
package com.mcawful.deckbuilder.aspects;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Advice class designed to handle HTTP response codes for various exceptions
 * that get thrown by the controllers.
 * 
 * @author Michael McAuliffe
 *
 */
@ControllerAdvice
@EnableWebMvc
public class GlobalControllerExceptionHandler {

	/**
	 * Method to handle the HTTP response code when a
	 * {@link DataIntegrityViolationException} occurs. Response code is 409.
	 */
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Could not add resource due to constraint violation.") // 409
	@ExceptionHandler(DataIntegrityViolationException.class)
	public void handleConflict() {
		// Method intentionally left empty
	}

	/**
	 * Method to handle the HTTP response code when a {@link NoSuchElementException}
	 * occurs. Response code is 404.
	 */
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested resource was not found.") // 404
	@ExceptionHandler(NoSuchElementException.class)
	public void handleNotFound() {
		// Method intentionally left empty
	}

	/**
	 * Method to handle the HTTP response when a {@link IllegalArgumentException}
	 * occurs. Response code is 400.
	 */
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "An illegal request was made.") // 400
	@ExceptionHandler(IllegalArgumentException.class)
	public void handleBadRequest() {
		// Method intentionally left empty
	}
}
