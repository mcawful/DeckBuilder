/**
 * 
 */
package com.mcawful.deckbuilder.exceptions;

/**
 * @author Michael McAuliffe
 *
 */
@SuppressWarnings("serial")
public class EmailNotUniqueException extends Exception {

	/**
	 * @param message
	 */
	public EmailNotUniqueException(String message) {
		super(message);
	}
}
