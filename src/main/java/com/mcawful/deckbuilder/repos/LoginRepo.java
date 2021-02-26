/**
 * 
 */
package com.mcawful.deckbuilder.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcawful.deckbuilder.daos.Login;

/**
 * Repository for {@link Login} objects.
 * 
 * @author Michael McAuliffe
 *
 */
@Repository
public interface LoginRepo extends JpaRepository<Login, Integer> {

	/**
	 * Finds a {@link Login} object by {@link String} username.
	 * 
	 * @param username the {@link String} username of the {@link Login} object to
	 *                 find.
	 * @return an {@link Optional} of a {@link Login} object.
	 */
	public Optional<Login> findByUsername(String username);

	/**
	 * Deletes a {@link Login} object by {@link String} username.
	 * 
	 * @param username the {@link String} username of the {@link Login} object to
	 *                 delete.
	 */
	public void deleteByUsername(String username);

}
