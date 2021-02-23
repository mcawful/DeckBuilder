/**
 * 
 */
package com.mcawful.deckbuilder.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcawful.deckbuilder.models.User;

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
	 * @param username The {@link String} username of the {@link User} object to
	 *                 find.
	 * @return An {@link Optional} of a {@link User} object.
	 */
	Optional<User> findByUsername(String username);
}
