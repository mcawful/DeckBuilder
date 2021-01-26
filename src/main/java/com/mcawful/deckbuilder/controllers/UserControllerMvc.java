/**
 * 
 */
package com.mcawful.deckbuilder.controllers;

import java.util.ArrayList;
import java.util.List;
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
import com.mcawful.deckbuilder.dtos.UserDto;
import com.mcawful.deckbuilder.exceptions.MalformattedEmailException;
import com.mcawful.deckbuilder.exceptions.MalformattedUsernameException;
import com.mcawful.deckbuilder.models.User;
import com.mcawful.deckbuilder.services.UserService;
import com.mcawful.deckbuilder.utils.DataValidator;

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

	UserService userService;

	@Autowired
	public UserControllerMvc(UserService userService) {
		super();
		this.userService = userService;
	}

	/**
	 * GET method for retrieving a {@link UserDto} object.
	 * 
	 * @param id The ID of the {@link UserDto} object
	 * @return The {@link UserDto} that matches the given ID
	 */
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public UserDto getUser(@PathVariable int id) {

		User user = this.userService.getUser(id);
		return new UserDto(user);
	}

	/**
	 * POST method for creating a new {@link UserDto} object.
	 * 
	 * @param userDto The {@link UserDto} to create
	 * @throws MalformattedUsernameException When the {@link UserDto} object's
	 *                                       {@link String} username field is
	 *                                       improperly formatted
	 * @throws MalformattedEmailException    When the {@link UserDto} object's
	 *                                       {@link String} email field is
	 *                                       improperly formatted
	 */
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createUser(@RequestBody UserDto userDto)
			throws MalformattedUsernameException, MalformattedEmailException {

		this.validateDto(userDto);
		this.userService.createOrUpdateUser(userDto.dtoToPojo());
	}

	/**
	 * PUT method for updating an existing {@link UserDto} object.
	 * 
	 * @param id      The ID of the existing {@link UserDto} object
	 * @param userDto The updated {@link UserDto} object
	 * @throws MalformattedUsernameException When the {@link UserDto} object's
	 *                                       {@link String} username field is
	 *                                       improperly formatted
	 * @throws MalformattedEmailException    When the {@link UserDto} object's
	 *                                       {@link String} email field is
	 *                                       improperly formatted
	 */
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateUser(@PathVariable int id, @RequestBody UserDto userDto)
			throws MalformattedUsernameException, MalformattedEmailException {

		this.validateDto(userDto);
		this.userService.getUser(id); // Checks that the element already exists
		this.userService.createOrUpdateUser(userDto.dtoToPojo());
	}

	/**
	 * DELETE method for removing a {@link UserDto} object.
	 * 
	 * @param id The ID of the {@link UserDto} to delete
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteUser(@PathVariable int id) {

		this.userService.deleteUser(id);
	}

	/**
	 * GET method for retrieving all {@link UserDto} objects.
	 * 
	 * @return A {@link List} of all {@link UserDto} objects
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
	 * Method that takes a {@link UserDto} object and throws exceptions if either
	 * the {@link String} username or {@link String} email field is not properly
	 * formatted.
	 * 
	 * @param userDto The given {@link UserDto} object to validate
	 * @throws MalformattedUsernameException When the {@link String} username is
	 *                                       improperly formatted
	 * @throws MalformattedEmailException    When the {@link String} email is
	 *                                       improperly formatted
	 */
	private void validateDto(UserDto userDto) throws MalformattedUsernameException, MalformattedEmailException {

		DataValidator dataValidator = new DataValidator();

		if (!dataValidator.isUsernameValid(userDto.getUsername()))
			throw new MalformattedUsernameException("Username is invalidly formatted.");

		if (!dataValidator.isEmailValid(userDto.getEmail()))
			throw new MalformattedEmailException("Email is invalidly formatted.");
	}
}
