/**
 * 
 */
package com.mcawful.deckbuilder.exceptions;

/**
 * @author Michael McAuliffe
 *
 */
@SuppressWarnings("serial")
public class MalformattedUsernameException extends Exception {

	/**
	 * @param message
	 */
	public MalformattedUsernameException(String message) {
		super(message);
	}
}
