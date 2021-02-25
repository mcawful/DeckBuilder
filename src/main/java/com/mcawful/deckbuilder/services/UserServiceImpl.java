/**
 * 
 */
package com.mcawful.deckbuilder.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mcawful.deckbuilder.daos.User;
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

	/**
	 * The {@link UserRepo} object.
	 */
	private UserRepo userRepo;

	/**
	 * Autowires the {@link UserRepo}.
	 * 
	 * @param userRepo
	 */
	@Autowired
	public UserServiceImpl(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	/**
	 * Method to handle a GET request for a {@link User} object. Calls the
	 * {@link UserRepo} repository's 'findById' method.
	 * 
	 * @param useranme the {@link String} username of the {@link User} object
	 * @return the related {@link User} object
	 * @throws NoSuchElementException when related {@link User} object cannot be
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
	 * @param user the {@link User} object to be created or updated
	 * @return the created or updated {@link User} object
	 * @throws IllegalArgumentException        when passed a null {@link User}
	 *                                         object
	 * @throws DataIntegrityViolationException when passed a {@link User} object
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
	 * @param id the ID of the existing {@link User} object
	 */
	@Override
	public void deleteUser(int id) {

		this.userRepo.deleteById(id);
	}

	/**
	 * Method to handle a GET request for all {@link User} objects. Calls the
	 * {@link UserRepo} repository's 'findAll' method.
	 * 
	 * @return a {@link List} of {@link User} objects
	 */
	@Override
	public List<User> getAllUsers() {

		return this.userRepo.findAll();
	}
}
