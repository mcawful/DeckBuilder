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
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public UserDto getUser(@PathVariable int id) {

		User user = this.userService.getUser(id);
		return new UserDto(user);
	}

	/**
	 * 
	 * @param userDto
	 * @throws MalformattedUsernameException
	 * @throws MalformattedEmailException
	 */
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createUser(@RequestBody UserDto userDto)
			throws MalformattedUsernameException, MalformattedEmailException {

		this.validateDto(userDto);
		this.userService.createOrUpdateUser(userDto.dtoToPojo());
	}

	/**
	 * 
	 * @param id
	 * @param userDto
	 * @throws MalformattedUsernameException
	 * @throws MalformattedEmailException
	 */
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateUser(@PathVariable int id, @RequestBody UserDto userDto)
			throws MalformattedUsernameException, MalformattedEmailException {

		this.validateDto(userDto);
		this.userService.getUser(id);
		this.userService.createOrUpdateUser(userDto.dtoToPojo());
	}

	/**
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteUser(@PathVariable int id) {

		this.userService.deleteUser(id);
	}

	/**
	 * 
	 * @return
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
	 * 
	 * @param userDto
	 * @throws MalformattedUsernameException
	 * @throws MalformattedEmailException
	 */
	private void validateDto(UserDto userDto) throws MalformattedUsernameException, MalformattedEmailException {

		DataValidator dataValidator = new DataValidator();

		if (!dataValidator.isUsernameValid(userDto.getUsername()))
			throw new MalformattedUsernameException("Username is invalidly formatted.");

		if (!dataValidator.isEmailValid(userDto.getEmail()))
			throw new MalformattedEmailException("Email is invalidly formatted.");
	}
}
