/**
 * 
 */
package com.mcawful.deckbuilder.dtos;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mcawful.deckbuilder.daos.Login;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Used with the Spring Security configuration to authorize and authenticate
 * users.
 * 
 * @author Michael McAuliffe
 *
 */
@Getter
@ToString
@EqualsAndHashCode
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
	@ToString.Exclude
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
	 * Constructor that takes in a {@link Login} to construct the fields.
	 * 
	 * @param login the {@link Login} object to construct from
	 */
	public UserDetailsDto(Login login) {
		super();
		if (!login.getRoles().isEmpty())
			this.authorities = Arrays.stream(login.getRoles().split(",")).map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		else
			this.authorities = Collections.<GrantedAuthority>emptyList();
		this.username = login.getUsername();
		this.password = login.getPassword();
		this.accountNonExpired = login.isAccountNonExpired();
		this.accountNonLocked = login.isAccountNonLocked();
		this.credentialsNonExpired = login.isCredentialsNonExpired();
		this.enabled = login.isEnabled();
	}
}
