/**
 * 
 */
package com.mcawful.deckbuilder.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.mcawful.deckbuilder.models.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This controller contains the endpoints relating to the {@link User} object.
 * This contains basic CRUD methods for GET, POST, PUT, and DELETE. There is
 * also a GET method for all {@link User} objects.
 * 
 * @author Michael McAuliffe
 *
 */
@RestController
public class UserController {

	public Mono<User> getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean createUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateUser(User user, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Flux<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
}
