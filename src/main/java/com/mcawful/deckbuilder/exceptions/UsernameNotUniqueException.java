/**
 * 
 */
package com.mcawful.deckbuilder.exceptions;

/**
 * @author Michael McAuliffe
 *
 */
public class UsernameNotUniqueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7621104670061532809L;

	/**
	 * @param message
	 */
	public UsernameNotUniqueException(String message) {
		super(message);
	}
}
