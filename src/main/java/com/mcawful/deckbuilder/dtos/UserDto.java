/**
 * 
 */
package com.mcawful.deckbuilder.dtos;

import com.mcawful.deckbuilder.daos.User;

import lombok.Value;

/**
 * A DTO object that acts as a front end representation of the {@link User}
 * object.
 * 
 * @author Michael McAuliffe
 *
 */
@Value
public class UserDto {

	/**
	 * {@link String} The username of the {@link UserDto} object.
	 */
	String username;

	/**
	 * {@link String} The email of the {@link UserDto} object.
	 */
	String email;

	/**
	 * {@link String} The date the {@link UserDto} object was created.
	 */
	String accountCreationDate;

	/**
	 * Boolean that denotes whether the {@link UserDto} object is currently active
	 * or not.
	 */
	boolean accountActive;

	/**
	 * Takes in a {@link User} object to construct the {@link UserDto} object
	 * fields.
	 * 
	 * @param user the {@link User} object
	 */
	public UserDto(User user) {

		this.username = user.getUsername();
		this.email = user.getEmail();
		this.accountCreationDate = user.getAccountCreationDate().toString();
		this.accountActive = user.isAccountActive();
	}

}
