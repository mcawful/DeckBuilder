/**
 * 
 */
package com.mcawful.deckbuilder.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.mcawful.deckbuilder.models.User;

/**
 * Interface for handling the logic for {@link User} objects. Designed for the
 * purpose of handling basic CRUD operations and calling the proper repository.
 * 
 * @author Michael McAuliffe
 *
 */
public interface UserService {

	/**
	 * Method meant to handle a GET operation for a {@link User} object.
	 * 
	 * @param id The ID of the {@link User} object
	 * @return The related {@link User} object
	 * @throws NoSuchElementException When related {@link User} object cannot be
	 *                                found in the database
	 */
	public User getUser(int id) throws NoSuchElementException;

	/**
	 * Method meant to handle a POST or PUT request for a {@link User} object.
	 * 
	 * @param user The {@link User} object to be added or updated
	 * @return The added or updated {@link User} object
	 * @throws IllegalArgumentException When passed a null {@link User} object
	 */
	public User createOrUpdateUser(User user) throws IllegalArgumentException;

	/**
	 * Method for handling a DELETE request for a {@link User} object.
	 * 
	 * @param id The ID of the existing {@link User} object
	 * @return The deleted {@link User} object
	 */
	public void deleteUser(int id);

	/**
	 * Method for handling a GET request for all {@link User} objects.
	 * 
	 * @return A {@link List} of {@link User} objects
	 */
	public List<User> getAllUsers();

}
