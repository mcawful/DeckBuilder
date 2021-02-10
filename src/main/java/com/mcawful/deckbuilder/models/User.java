/**
 * 
 */
package com.mcawful.deckbuilder.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This model contains the basic information for a user.
 * 
 * @author Michael McAuliffe
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
public class User {

	/**
	 * The ID of the {@link User} object.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	/**
	 * {@link String} The username of the {@link User} object.
	 */
	@Column(unique = true)
	private String username;

	/**
	 * {@link String} The email of the {@link User} object.
	 */
	@Column(unique = true)
	private String email;

	/**
	 * {@link LocalDateTime} The date the {@link User} object was created.
	 */
	@CreatedDate
	@Column
	private LocalDateTime accountCreationDate;

	/**
	 * Boolean that denotes whether the {@link User} object is currently active or
	 * not.
	 */
	@Column
	private boolean accountActive;
}
