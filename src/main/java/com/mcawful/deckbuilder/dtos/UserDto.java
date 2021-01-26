/**
 * 
 */
package com.mcawful.deckbuilder.dtos;

import java.time.LocalDateTime;
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
	public UserDto(String username, String email) {

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
	public UserDto(User user) {

		this.setUsername(user.getUsername());
		this.setEmail(user.getEmail());
		this.setAccountCreationDate(user.getAccountCreationDate().toString());
		this.setAccountActive(user.isAccountActive());
	}

	/**
	 * Method that will return a {@link User} object based on the fields of the
	 * {@link UserDto} object.
	 * 
	 * @return A {@link User} object
	 */
	@Override
	public User dtoToPojo() {

		LocalDateTime dateTime = null;

		if (!this.accountCreationDate.isEmpty())
			dateTime = LocalDateTime.parse(this.accountCreationDate);

		return new User(0, this.username, this.email, dateTime, this.accountActive);
	}

}
