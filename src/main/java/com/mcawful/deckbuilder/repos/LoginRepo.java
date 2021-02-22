/**
 * 
 */
package com.mcawful.deckbuilder.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcawful.deckbuilder.models.Login;

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
	 * @param username The {@link String} username of the {@link Login} object to
	 *                 find.
	 * @return An {@link Optional} of a {@link Login} object.
	 */
	Optional<Login> findByUsername(String username);
}
