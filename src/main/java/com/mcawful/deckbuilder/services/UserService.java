/**
 * 
 */
package com.mcawful.deckbuilder.services;

import java.util.List;

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
	 * @param id The ID of the {@link User} object.
	 * @return The related {@link User} object.
	 */
	public User getUser(int id);

	/**
	 * Method meant to handle a POST request for a {@link User} object.
	 * 
	 * @param user The {@link User} object to be added.
	 * @return The added {@link User} object.
	 */
	public User createUser(User user);

	/**
	 * Method for handling a PUT request for a {@link User} object.
	 * 
	 * @param user The {@link User} object to be updated.
	 * @param id   The ID of the existing {@link User} object.
	 * @return The updated {@link User} object.
	 */
	public User updateUser(User user, int id);

	/**
	 * Method for handling a DELETE request for a {@link User} object.
	 * 
	 * @param id The ID of the existing {@link User} object.
	 * @return The deleted {@link User} object.
	 */
	public User deleteUser(int id);

	/**
	 * Method for handling a GET request for all {@link User} objects.
	 * 
	 * @return A {@link List} of {@link User} objects.
	 */
	public List<User> getAllUsers();

}
