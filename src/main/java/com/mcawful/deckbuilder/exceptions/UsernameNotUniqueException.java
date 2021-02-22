/**
 * 
 */
package com.mcawful.deckbuilder.exceptions;

/**
 * @author Michael McAuliffe
 *
 */
@SuppressWarnings("serial")
public class UsernameNotUniqueException extends Exception {

	/**
	 * @param message
	 */
	public UsernameNotUniqueException(String message) {
		super(message);
	}
}
