/**
 * 
 */
package com.mcawful.deckbuilder.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcawful.deckbuilder.dtos.UserDto;
import com.mcawful.deckbuilder.exceptions.MalformattedEmailException;
import com.mcawful.deckbuilder.exceptions.MalformattedUsernameException;
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
	public UserDto getUser(@PathVariable int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping
	public boolean createUser(@RequestBody UserDto userDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@PutMapping("/{id}")
	public boolean updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@DeleteMapping("/{id}")
	public boolean deleteUser(@PathVariable int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@GetMapping
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
}
