/**
 * 
 */
package com.mcawful.deckbuilder.repos;

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

}