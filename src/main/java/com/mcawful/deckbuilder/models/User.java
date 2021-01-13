/**
 * 
 */
package com.mcawful.deckbuilder.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This model contains the basic information for a user.
 * 
 * @author Michael McAuliffe
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	/**
	 * The ID of the {@link User} object.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * {@link String} The username of the {@link User} object.
	 */
	private String username;

	/**
	 * {@link String} The email of the {@link User} object.
	 */
	private String email;

	/**
	 * {@link LocalDateTime} The date the {@link User} object was created.
	 */
	private LocalDateTime accountCreationDate;

	/**
	 * Boolean that denotes whether the {@link User} object is currently active or
	 * not.
	 */
	private boolean accountActive;
}
