/**
 * 
 */
package com.mcawful.deckbuilder.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains methods to verify various types of data to ensure the
 * given data matches the expected constraints.
 * 
 * @author Michael McAuliffe
 *
 */
public class DataValidator {

	/**
	 * Checks given username {@link String} to see if it matches the regex
	 * constraints. Regex constraints are: must be between 4-30 characters, cannot
	 * start with a '_' or a '.', cannot contain '__' or '_.' or '._' or '..' on the
	 * inside, cannot end with a '_' or a '.', and must be alphanumeric and can
	 * contain '_' or '.' characters.
	 * 
	 * @param username The {@link String} to be checked
	 * @return True if given {@link String} matches the regex
	 */
	public boolean isUsernameValid(String username) {

		String regex = "^(?=.{4,30}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(username);

		return matcher.matches();
	}

	/**
	 * Checks given email {@link String} to see if it matches the regex constraints.
	 * Regex constraints follow the RFC 5322 official standard for general emails.
	 * 
	 * @param email The {@link String} to be checked
	 * @return True if given {@link String} matches the regex
	 */
	public boolean isEmailValid(String email) {

		String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)"
				+ "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\"
				+ "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
				+ "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)"
				+ "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
				+ "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\"
				+ "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}
}
