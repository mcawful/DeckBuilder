/**
 * 
 */
package com.mcawful.deckbuilder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to handle situations where an email is not in a valid format.
 * 
 * @author Michael McAuliffe
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email is not in a vaild format.")
public class MalformattedEmailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 448952964645982603L;

	/**
	 * The default constructor for the {@link MalformattedEmailException} class.
	 * 
	 * @param message The {@link String} error message
	 */
	public MalformattedEmailException(String message) {
		super(message);
	}
}
