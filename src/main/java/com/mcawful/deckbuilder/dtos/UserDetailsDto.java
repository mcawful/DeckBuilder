/**
 * 
 */
package com.mcawful.deckbuilder.dtos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mcawful.deckbuilder.daos.Login;

import lombok.Data;

/**
 * Used with the Spring Security configuration to authorize and authenticate
 * users.
 * 
 * @author Michael McAuliffe
 *
 */
@Data
public class UserDetailsDto implements UserDetails {

	private static final long serialVersionUID = 6476506223560386489L;

	/**
	 * The {@link List} of {@link GrantedAuthority} roles for a given user.
	 */
	private List<GrantedAuthority> authorities;

	/**
	 * The {@link String} username of a given user.
	 */
	private String username;

	/**
	 * The {@link String} password of a given user.
	 */
	private String password;

	/**
	 * Indicates if the account is expired.
	 */
	private boolean accountNonExpired;

	/**
	 * Indicates if the account is locked.
	 */
	private boolean accountNonLocked;

	/**
	 * Indicates if the account credentials are expired.
	 */
	private boolean credentialsNonExpired;
	/**
	 * Indicates if the account is enabled.
	 */
	private boolean enabled;

	/**
	 * Constructor that takes in a {@link Login} object to construct the fields.
	 * 
	 * @param login the {@link Login} object to construct from
	 */
	public UserDetailsDto(Login login) {
		super();
		this.setAuthorities(Arrays.stream(login.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()));
		this.setUsername(login.getUsername());
		this.setPassword(login.getPassword());
		this.setAccountNonExpired(login.isAccountNonExpired());
		this.setAccountNonLocked(login.isAccountNonLocked());
		this.setCredentialsNonExpired(login.isCredentialsNonExpired());
		this.setEnabled(login.isEnabled());
	}
}
