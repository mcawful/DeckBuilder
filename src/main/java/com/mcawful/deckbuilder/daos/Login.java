/**
 * 
 */
package com.mcawful.deckbuilder.daos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 * Model used to save user login information in the database.
 * 
 * @author Michael McAuliffe
 *
 */
@Entity
@Data
public class Login {

	/**
	 * The id of the {@link Login} object.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	/**
	 * The {@link String} of the authorized roles for a given user.
	 */
	@Column
	private String roles;

	/**
	 * The {@link String} username of the {@link Login} object.
	 */
	@Column(unique = true)
	private String username;

	/**
	 * The {@link String} password of the {@link Login} object.
	 */
	@Column
	private String password;

	/**
	 * Indicates if the account is expired.
	 */
	@Column
	private boolean accountNonExpired;

	/**
	 * Indicates if the account is locked.
	 */
	@Column
	private boolean accountNonLocked;

	/**
	 * Indicates if the account credentials are expired.
	 */
	@Column
	private boolean credentialsNonExpired;

	/**
	 * Indicates if the account is enabled.
	 */
	@Column
	private boolean enabled;
}
