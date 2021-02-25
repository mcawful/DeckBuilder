/**
 * 
 */
package com.mcawful.deckbuilder.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A DTO object designed for creating a new user account.
 * 
 * @author Michael McAuliffe
 *
 */
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CreateUserDto {

	/**
	 * The {@link String} username field.
	 */
	private String username;

	/**
	 * The {@link String} password field.
	 */
	private String password;

	/**
	 * The {@link String} email field.
	 */
	private String email;

}
