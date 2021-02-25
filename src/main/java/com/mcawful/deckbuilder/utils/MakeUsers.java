/**
 * 
 */
package com.mcawful.deckbuilder.utils;

import java.time.LocalDateTime;

import com.mcawful.deckbuilder.daos.User;
import com.mcawful.deckbuilder.dtos.CreateUserDto;
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
	 * @param userDto the {@link UserDto} to convert
	 * @return a {@link User} object
	 */
	public User convert(UserDto userDto) {

		return new User(0, userDto.getUsername().toLowerCase(), userDto.getEmail().toLowerCase(),
				LocalDateTime.parse(userDto.getAccountCreationDate()), userDto.isAccountActive());
	}

	/**
	 * Creates a {@link User} object based off of a {@link CreateUserDto} object.
	 * 
	 * @param createUserDto the {@link CreateUserDto} object to convert
	 * @return a {@link User} object
	 */
	public User convert(CreateUserDto createUserDto) {

		return new User(0, createUserDto.getUsername().toLowerCase(), createUserDto.getEmail().toLowerCase(),
				LocalDateTime.now(), true);
	}

}
