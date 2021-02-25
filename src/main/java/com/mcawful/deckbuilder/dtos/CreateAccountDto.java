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
public class CreateAccountDto {

	/**
	 * {@link String} The username of the {@link CreateAccountDto} object.
	 */
	private String username;

	/**
	 * {@link String} The password of the {@link CreateAccountDto} object.
	 */
	private String password;

	/**
	 * {@link String} The email of the {@link CreateAccountDto} object.
	 */
	private String email;

}
