/**
 * 
 */
package com.mcawful.deckbuilder.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A DTO object designed for updating existing user accounts.
 * 
 * @author Michael McAuliffe
 *
 */
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UpdateAccountDto {

	/**
	 * {@link String} The username of the {@link UpdateAccountDto} object.
	 */
	String username;

	/**
	 * {@link String} The password of the {@link UpdateAccountDto} object.
	 */
	String password;

	/**
	 * {@link String} The email of the {@link UpdateAccountDto} object.
	 */
	String email;

	/**
	 * Boolean that denotes whether the {@link UpdateAccountDto} object is currently
	 * active or not.
	 */
	boolean accountActive;
}
