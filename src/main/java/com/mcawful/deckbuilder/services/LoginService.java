/**
 * 
 */
package com.mcawful.deckbuilder.services;

import org.springframework.dao.DataIntegrityViolationException;

import com.mcawful.deckbuilder.daos.Login;

/**
 * @author Michael McAuliffe
 *
 */
public interface LoginService {

	/**
	 * Method meant to handle a POST or PUT request for a {@link Login} object.
	 * 
	 * @param login The {@link Login} object to create or update
	 * @return the created or updated {@link Login} object
	 * @throws IllegalArgumentException        when passed a null {@link Login}
	 *                                         object
	 * @throws DataIntegrityViolationException when passed a {@link Login} object
	 *                                         with a field or fields that violates
	 *                                         database constraints
	 */
	public Login createOrUpdateLogin(Login login) throws IllegalArgumentException, DataIntegrityViolationException;

	/**
	 * Method meant to handle a DELETE request for a {@link Login} Object.
	 * 
	 * @param username The {@link String} username of the existing {@link Login}
	 *                 object
	 */
	public void deleteLogin(String username);
}