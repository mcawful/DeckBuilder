/**
 * 
 */
package com.mcawful.deckbuilder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to handle situations when a database update is attempted where no
 * persistent related {@link Object} exists.
 * 
 * @author Michael McAuliffe
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cannot perform update, entity does not exist.")
public class NoSuchEntityToUpdateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6948672029082413658L;

	/**
	 * The default constructor for the {@link NoSuchEntityToUpdateException} class.
	 * 
	 * @param message The {@link String} error message
	 */
	public NoSuchEntityToUpdateException(String message) {
		super(message);
	}
}
