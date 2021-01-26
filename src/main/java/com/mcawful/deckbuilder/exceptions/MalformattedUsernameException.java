/**
 * 
 */
package com.mcawful.deckbuilder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Michael McAuliffe
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Username is not in a vaild format.")
public class MalformattedUsernameException extends Exception {

	/**
	 * @param message
	 */
	public MalformattedUsernameException(String message) {
		super(message);
	}
}
