/**
 * 
 */
package com.mcawful.deckbuilder.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.mcawful.deckbuilder.models.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author linkm
 *
 */
@RestController
public class UserController {

	public Mono<User> getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Mono<User> createUser(User user) {
		// TODO Auto-generated method stub
		return null;
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
