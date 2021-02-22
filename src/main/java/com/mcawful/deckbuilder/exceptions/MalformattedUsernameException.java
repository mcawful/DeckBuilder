/**
 * 
 */
package com.mcawful.deckbuilder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to handle situations where a username is not in a valid format.
 * 
 * @author Michael McAuliffe
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Username is not in a vaild format.")
public class MalformattedUsernameException extends Exception {

	/**
	 * The default constructor for the {@link MalformattedUsernameException} class.
	 * 
	 * @param message The {@link String} error message
	 */
	public MalformattedUsernameException(String message) {
		super(message);
	}
}
