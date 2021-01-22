/**
 * 
 */
package com.mcawful.deckbuilder.controllers;

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
import com.mcawful.deckbuilder.models.User;
import com.mcawful.deckbuilder.services.UserService;

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

	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public UserDto getUser(@PathVariable int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createUser(@RequestBody UserDto userDto) {
		// TODO Auto-generated method stub
	}

	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
		// TODO Auto-generated method stub
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteUser(@PathVariable int id) {
		// TODO Auto-generated method stub
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
}
