/**
 * 
 */
package com.mcawful.deckbuilder.daos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model used to save user login information in the database.
 * 
 * @author Michael McAuliffe
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	@ToString.Exclude
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

	/**
	 * Constructor for creating new user {@link Login} objects.
	 * 
	 * @param roles    {@link String} of authorized user roles
	 * @param username {@link String} of user's username
	 * @param password {@link String} of user's password
	 */
	public Login(String roles, String username, String password) {
		this.setId(0);
		this.setRoles(roles);
		this.setUsername(username);
		this.setPassword(password);
		this.setAccountNonExpired(true);
		this.setAccountNonLocked(true);
		this.setCredentialsNonExpired(true);
		this.setEnabled(true);
	}
}
