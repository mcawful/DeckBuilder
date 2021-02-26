/**
 * 
 */
package com.mcawful.deckbuilder.utils;

import java.time.LocalDateTime;

import com.mcawful.deckbuilder.daos.User;
import com.mcawful.deckbuilder.dtos.CreateAccountDto;
import com.mcawful.deckbuilder.dtos.UpdateAccountDto;
import com.mcawful.deckbuilder.dtos.UserDto;

import lombok.experimental.UtilityClass;

/**
 * A utility class with methods to create {@link User} objects from related DTO
 * objects.
 * 
 * @author Michael McAuliffe
 *
 */
@UtilityClass
public class MakeUsers {

	/**
	 * Creates a {@link User} object based off of a {@link UserDto} object.
	 * 
	 * @param dto the {@link UserDto} to convert
	 * @return a {@link User} object
	 */
	public User convert(UserDto dto) {

		return new User(0, dto.getUsername().toLowerCase(), dto.getEmail().toLowerCase(),
				LocalDateTime.parse(dto.getAccountCreationDate()), dto.isAccountActive());
	}

	/**
	 * Creates a {@link User} object based off of a {@link CreateAccountDto} object.
	 * 
	 * @param dto the {@link CreateAccountDto} object to convert
	 * @return a {@link User} object
	 */
	public User convert(CreateAccountDto dto) {

		return new User(0, dto.getUsername().toLowerCase(), dto.getEmail().toLowerCase(), LocalDateTime.now(), true);
	}

	/**
	 * Creates a {@link User} object based off of a {@link UpdateAccountDto} object.
	 * 
	 * @param dto the {@link UpdateAccountDto} object to convert
	 * @return a {@link User} object
	 */
	public User convert(UpdateAccountDto dto) {

		return new User(0, dto.getUsername(), dto.getEmail(), null, dto.isAccountActive());
	}
}
