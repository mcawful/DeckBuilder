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
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Username is not in a vaild format.")
public class MalformattedUsernameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2208187420960063181L;

	/**
	 * The default constructor for the {@link MalformattedUsernameException} class.
	 * 
	 * @param message the {@link String} error message
	 */
	public MalformattedUsernameException(String message) {
		super(message);
	}
}
