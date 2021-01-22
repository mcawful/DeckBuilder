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
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email is not in a vaild format.")
public class MalformattedEmailException extends Exception {

	/**
	 * @param message
	 */
	public MalformattedEmailException(String message) {
		super(message);
	}
}
