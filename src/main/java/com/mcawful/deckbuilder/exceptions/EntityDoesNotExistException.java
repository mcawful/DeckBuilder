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
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Entity does not exist in database.")
public class EntityDoesNotExistException extends Exception {

	/**
	 * @param message
	 */
	public EntityDoesNotExistException(String message) {
		super(message);
	}
	
	

}
