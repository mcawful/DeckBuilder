/**
 * 
 */
package com.mcawful.deckbuilder.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security configuration. Handles authentication and authorization. Also
 * provides a password encoder bean for BCrypt.
 * 
 * @author Michael McAuliffe
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	UserDetailsService userDetailsService;

	/**
	 * Autowires the {@link UserDetailsService}.
	 * 
	 * @param userDetailsService
	 */
	@Autowired
	public SecurityConfiguration(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(this.userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").permitAll().and().formLogin();
		http.csrf().disable();
	}

	/**
	 * Creates a new {@link BCryptPasswordEncoder} and returns it.
	 * 
	 * @return {@link BCryptPasswordEncoder}
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}
}
