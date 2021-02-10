/**
 * 
 */
package com.mcawful.deckbuilder.exceptions;

/**
 * @author Michael McAuliffe
 *
 */
public class EmailNotUniqueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3550027585027753592L;

	/**
	 * @param message
	 */
	public EmailNotUniqueException(String message) {
		super(message);
	}
}
