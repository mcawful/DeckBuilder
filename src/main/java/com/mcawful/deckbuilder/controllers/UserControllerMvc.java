/**
 * 
 */
package com.mcawful.deckbuilder.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mcawful.deckbuilder.dtos.CreateAccountDto;
import com.mcawful.deckbuilder.dtos.UpdateAccountDto;
import com.mcawful.deckbuilder.daos.User;
import com.mcawful.deckbuilder.dtos.UserDto;
import com.mcawful.deckbuilder.exceptions.MalformattedEmailException;
import com.mcawful.deckbuilder.exceptions.MalformattedUsernameException;
import com.mcawful.deckbuilder.exceptions.NoSuchEntityToUpdateException;
import com.mcawful.deckbuilder.services.UserService;
import com.mcawful.deckbuilder.utils.DataValidator;
import com.mcawful.deckbuilder.utils.MakeUsers;

/**
 * This controller contains the endpoints relating to the {@link User} object.
 * This contains basic CRUD methods for GET, POST, PUT, and DELETE. There is
 * also a GET method for all {@link User} objects.
 * 
 * @author Michael McAuliffe
 *
 */
@RestController
@RequestMapping("/user")
public class UserControllerMvc {

	/**
	 * The {@link UserService} object.
	 */
	UserService userService;

	/**
	 * Autowires the {@link UserService}.
	 * 
	 * @param userService
	 */
	@Autowired
	public UserControllerMvc(UserService userService) {
		super();
		this.userService = userService;
	}

	/**
	 * GET method for retrieving a {@link UserDto} object.
	 * 
	 * @param username the {@link String} username of the {@link UserDto} object
	 * @return the {@link UserDto} that matches the given {@link String} username
	 */
	@GetMapping("/{username}")
	@ResponseStatus(value = HttpStatus.OK)
	public UserDto getUser(@PathVariable String username) {

		User user = this.userService.getUser(username);
		return new UserDto(user);
	}

	/**
	 * POST method for creating a new {@link User} and {@link Login} object.
	 * 
	 * @param dto the {@link CreateAccountDto} to create from
	 * @throws MalformattedUsernameException when the {@link CreateAccountDto}
	 *                                       object's {@link String} username field
	 *                                       is improperly formatted
	 * @throws MalformattedEmailException    when the {@link CreateAccountDto}
	 *                                       object's {@link String} email field is
	 *                                       improperly formatted
	 */
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createUser(@RequestBody CreateAccountDto dto)
			throws MalformattedUsernameException, MalformattedEmailException {

		this.validateDto(dto);

		this.userService.createOrUpdateUser(MakeUsers.convert(dto));
	}

	/**
	 * PUT method for updating an existing {@link User} object.
	 * 
	 * @param username the {@link String} username of the existing {@link UserDto}
	 *                 object
	 * @param dto      the {@link UserDto} object to update from
	 * @throws MalformattedUsernameException when the {@link UserDto} object's
	 *                                       {@link String} username field is
	 *                                       improperly formatted
	 * @throws MalformattedEmailException    when the {@link UserDto} object's
	 *                                       {@link String} email field is
	 *                                       improperly formatted
	 * @throws NoSuchEntityToUpdateException when the given {@link String} username
	 *                                       does not match to any existing
	 *                                       {@link UserDto} objects
	 */
	@PutMapping("/{username}")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateUser(@PathVariable String username, @RequestBody UpdateAccountDto dto)
			throws MalformattedUsernameException, MalformattedEmailException, NoSuchEntityToUpdateException {

		this.validateDto(dto);

		try {
			this.userService.getUser(username);
		} catch (NoSuchElementException e) {
			throw new NoSuchEntityToUpdateException("Cannot perform update, entity does not exist.");
		}

		this.userService.createOrUpdateUser(MakeUsers.convert(dto));
	}

	/**
	 * DELETE method for removing a {@link User} object.
	 * 
	 * @param id the ID of the {@link User} to delete
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteUser(@PathVariable int id) {

		this.userService.deleteUser(id);
	}

	/**
	 * GET method for retrieving all {@link User} objects.
	 * 
	 * @return a {@link List} of all {@link UserDto} objects
	 */
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<UserDto> getAllUsers() {

		List<User> userList = this.userService.getAllUsers();
		List<UserDto> userDtoList = new ArrayList<>();

		for (User user : userList) {
			userDtoList.add(new UserDto(user));
		}

		return userDtoList;
	}

	/**
	 * Method that takes a {@link UserLoginDto} object and throws exceptions if
	 * either the {@link String} username field or {@link String} email field is not
	 * properly formatted. The {@link String} username field is checked before the
	 * {@link String} email field.
	 * 
	 * @param dto the given {@link UserLoginDto} object to validate
	 * @throws MalformattedUsernameException when the {@link String} username is
	 *                                       improperly formatted
	 * @throws MalformattedEmailException    when the {@link String} email is
	 *                                       improperly formatted
	 */
	private void validateDto(CreateAccountDto dto) throws MalformattedUsernameException, MalformattedEmailException {

		if (!DataValidator.isUsernameValid(dto.getUsername().toLowerCase()))
			throw new MalformattedUsernameException("Username is invalidly formatted.");

		if (!DataValidator.isEmailValid(dto.getEmail().toLowerCase()))
			throw new MalformattedEmailException("Email is invalidly formatted.");
	}

	/**
	 * Method that takes a {@link UpdateAccountDto} object and throws exceptions if
	 * either the {@link String} username field or {@link String} email field is not
	 * properly formatted. The {@link String} username field is checked before the
	 * {@link String} email field.
	 * 
	 * @param dto the given {@link UpdateAccountDto} object to validate
	 * @throws MalformattedUsernameException when the {@link String} username is
	 *                                       improperly formatted
	 * @throws MalformattedEmailException    when the {@link String} email is
	 *                                       improperly formatted
	 */
	private void validateDto(UpdateAccountDto dto) throws MalformattedUsernameException, MalformattedEmailException {

		if (!DataValidator.isUsernameValid(dto.getUsername().toLowerCase()))
			throw new MalformattedUsernameException("Username is invalidly formatted.");

		if (!DataValidator.isEmailValid(dto.getEmail().toLowerCase()))
			throw new MalformattedEmailException("Email is invalidly formatted.");
	}
}
