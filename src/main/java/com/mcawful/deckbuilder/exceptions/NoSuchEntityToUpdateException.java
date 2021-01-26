/**
 * 
 */
package com.mcawful.deckbuilder.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Michael McAuliffe
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cannot perform update, entity does not exist.")
public class NoSuchEntityToUpdateException extends NoSuchElementException {

	/**
	 * @param message
	 */
	public NoSuchEntityToUpdateException(String message) {
		super(message);
	}
}
