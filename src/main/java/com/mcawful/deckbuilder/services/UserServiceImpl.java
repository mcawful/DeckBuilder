/**
 * 
 */
package com.mcawful.deckbuilder.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcawful.deckbuilder.models.User;
import com.mcawful.deckbuilder.repos.UserRepo;

/**
 * Service that handles the logic for dealing with {@link User} objects. Makes
 * calls to the {@link UserRepo} for basic CRUD operations.
 * 
 * @author Michael McAuliffe
 *
 */

@Service
public class UserServiceImpl implements UserService {

	private UserRepo userRepo;

	@Autowired
	public UserServiceImpl(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public User getUser(int id) throws NoSuchElementException {

		return this.userRepo.findById(id).get();
	}

	@Override
	public User createOrUpdateUser(User user) throws IllegalArgumentException {

		return this.userRepo.save(user);
	}

	@Override
	public void deleteUser(int id) {

		this.userRepo.deleteById(id);
	}

	@Override
	public List<User> getAllUsers() {

		return this.userRepo.findAll();
	}
}
