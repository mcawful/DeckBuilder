/**
 * 
 */
package com.mcawful.deckbuilder.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mcawful.deckbuilder.dtos.UserDetailsDto;
import com.mcawful.deckbuilder.models.Login;
import com.mcawful.deckbuilder.repos.LoginRepo;

/**
 * Implementation of the {@link UserDetailsService} for {@link Login} objects.
 * 
 * @author Michael McAuliffe
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	LoginRepo loginRepo;

	/**
	 * Autowires the {@link LoginRepo}.
	 * 
	 * @param loginRepo
	 */
	@Autowired
	public UserDetailsServiceImpl(LoginRepo loginRepo) {
		super();
		this.loginRepo = loginRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Login> login = loginRepo.findByUsername(username);

		return login.map(UserDetailsDto::new)
				.orElseThrow(() -> new UsernameNotFoundException("Could not find username: '" + username + "'"));
	}

}
