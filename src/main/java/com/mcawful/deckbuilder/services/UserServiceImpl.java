/**
 * 
 */
package com.mcawful.deckbuilder.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

	/**
	 * Method to handle a GET request for a {@link User} object. Calls the
	 * {@link UserRepo} repository's 'findById' method.
	 * 
	 * @param useranme The {@link String} username of the {@link User} object
	 * @return The related {@link User} object
	 * @throws NoSuchElementException When related {@link User} object cannot be
	 *                                found in the database
	 */
	@Override
	public User getUser(String username) throws NoSuchElementException {

		return this.userRepo.findByUsername(username).orElseThrow(NoSuchElementException::new);
	}

	/**
	 * Method to handle a POST or PUT request for a {@link User} object. Calls the
	 * {@link UserRepo} repository's 'save' method.
	 * 
	 * @param user The {@link User} object to be added or updated
	 * @return The added or updated {@link User} object
	 * @throws IllegalArgumentException        When passed a null {@link User}
	 *                                         object
	 * @throws DataIntegrityViolationException When passed a {@link User} object
	 *                                         with a field or fields that violates
	 *                                         database constraints
	 */
	@Override
	public User createOrUpdateUser(User user) throws DataIntegrityViolationException {

		return this.userRepo.save(user);
	}

	/**
	 * Method to handle a DELETE request for a {@link User} object. Calls the
	 * {@link UserRepo} repository's 'deleteById' method.
	 * 
	 * @param id The ID of the existing {@link User} object
	 */
	@Override
	public void deleteUser(int id) {

		this.userRepo.deleteById(id);
	}

	/**
	 * Method to handle a GET request for all {@link User} objects. Calls the
	 * {@link UserRepo} repository's 'findAll' method.
	 * 
	 * @return A {@link List} of {@link User} objects
	 */
	@Override
	public List<User> getAllUsers() {

		return this.userRepo.findAll();
	}
}
