/**
 * 
 */
package com.mcawful.deckbuilder.exceptions;

/**
 * @author Michael McAuliffe
 *
 */
@SuppressWarnings("serial")
public class MalformattedEmailException extends Exception {

	/**
	 * @param message
	 */
	public MalformattedEmailException(String message) {
		super(message);
	}
}
