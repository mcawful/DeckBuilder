/**
 * 
 */
package com.mcawful.deckbuilder.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcawful.deckbuilder.daos.User;

/**
 * Repository for the {@link User} object.
 * 
 * @author Michael McAuliffe
 *
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	/**
	 * Finds a {@link User} object by {@link String} username.
	 * 
	 * @param username the {@link String} username of the {@link User} object to
	 *                 find.
	 * @return an {@link Optional} of a {@link User} object.
	 */
	Optional<User> findByUsername(String username);
}
