/**
 * 
 */
package com.mcawful.deckbuilder.dtos;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mcawful.deckbuilder.exceptions.MalformattedEmailException;
import com.mcawful.deckbuilder.exceptions.MalformattedUsernameException;
import com.mcawful.deckbuilder.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO object that acts as a front end representation of the {@link User}
 * object.
 * 
 * @author Michael McAuliffe
 *
 */
@Data
@NoArgsConstructor
public class UserDto implements DtoConverter<User> {

	/**
	 * {@link String} The username of the {@link UserDto} object.
	 */
	private String username;

	/**
	 * {@link String} The email of the {@link UserDto} object.
	 */
	private String email;

	/**
	 * {@link String} The date the {@link UserDto} object was created.
	 */
	private String accountCreationDate;

	/**
	 * Boolean that denotes whether the {@link UserDto} object is currently active
	 * or not.
	 */
	private boolean accountActive;

	/**
	 * Constructs a {@link UserDto} from a {@link String} username and
	 * {@link String} email. Also sets the accountActive field to true. Designed for
	 * initial account creation.
	 * 
	 * @param username The {@link String} username field
	 * @param email    The {@link String} email field
	 */
	public UserDto(String username, String email) throws MalformattedEmailException, MalformattedUsernameException {

		this.setUsername(username);
		this.setEmail(email);
		this.setAccountActive(true);
	}

	/**
	 * Takes in a {@link User} object to construct the {@link UserDto} object
	 * fields.
	 * 
	 * @param user The {@link User} object
	 */
	public UserDto(User user) throws MalformattedEmailException, MalformattedUsernameException {

		this.setUsername(user.getUsername());
		this.setEmail(user.getEmail());
		this.setAccountCreationDate(user.getAccountCreationDate().toString());
		this.setAccountActive(user.isAccountActive());
	}

	/**
	 * Sets the username field of the {@link UserDto} object.
	 * 
	 * @param username The {@link String} to set the username field of the
	 *                 {@link UserDto} object
	 * @throws MalformattedUsernameException When the given {@link String} username
	 *                                       does not match the constraints of the
	 *                                       regex
	 */
	public void setUsername(String username) throws MalformattedUsernameException {

		if (isUsernameValid(username))
			this.username = username;
		else
			throw new MalformattedUsernameException("The given username '" + username + "' is invalid.");
	}

	/**
	 * Sets the email field of the {@link UserDto} object.
	 * 
	 * @param email The {@link String} to set the email field of the {@link UserDto}
	 *              object
	 * @throws MalformattedEmailException When the given {@link String} email does
	 *                                    not match the constraints of the regex
	 */
	public void setEmail(String email) throws MalformattedEmailException {

		email = email.toLowerCase();

		if (isEmailValid(email))
			this.email = email;
		else
			throw new MalformattedEmailException("The given email '" + email + "' is invaild.");
	}

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
	private boolean isUsernameValid(String username) {

		String usernameRegex = "^(?=.{4,30}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";

		Pattern pattern = Pattern.compile(usernameRegex);
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
	private boolean isEmailValid(String email) {

		String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)"
				+ "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\"
				+ "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
				+ "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)"
				+ "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
				+ "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\"
				+ "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	/**
	 * Method that will return a {@link User} object based on the fields of the
	 * {@link UserDto} object.
	 * 
	 * @return A {@link User} object
	 */
	@Override
	public User dtoToPojo() {

		LocalDateTime dateTime = LocalDateTime.parse(this.accountCreationDate);

		return new User(0, this.username, this.email, dateTime, this.accountActive);
	}

}
