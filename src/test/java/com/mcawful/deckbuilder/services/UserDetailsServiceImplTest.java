/**
 * 
 */
package com.mcawful.deckbuilder.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mcawful.deckbuilder.dtos.UserDetailsDto;
import com.mcawful.deckbuilder.models.Login;
import com.mcawful.deckbuilder.repos.LoginRepo;

/**
 * @author Michael McAuliffe
 *
 */
@SpringBootTest
class UserDetailsServiceImplTest {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@MockBean
	private LoginRepo loginRepo;

	private Optional<Login> optionalLogin;

	private Login login;

	private UserDetailsDto userDetails;

	private String username;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		this.optionalLogin = Optional.of(new Login("USER,ADMIN", username, "password"));
		this.login = optionalLogin.get();
		this.loginRepo.save(this.login);
		this.userDetails = new UserDetailsDto(login);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Tests {@link UserDetailsServiceImpl} 'findByUsername' method when the given
	 * {@link String} username matches a corresponding {@link String} username in a
	 * {@link Login} object and the {@link List}<{@link GrantedAuthority}> in the
	 * {@link UserDetails} object created from the {@link Login} object is not
	 * empty. Test asserts that the returned {@link UserDetails} object equals what
	 * is expected and verifies that the {@link LoginRepo} 'findByUsername' method
	 * was called.
	 */
	@Test
	void loadUserByUsernameTest_UserExistsAndHasAuth() {

		when(this.loginRepo.findByUsername(this.username)).thenReturn(this.optionalLogin);

		UserDetails returned = this.userDetailsService.loadUserByUsername(this.username);

		assertEquals(this.userDetails, returned, "UserDetailsService.loadUserByUsername(" + this.username
				+ ") did not return expected UserDetails object. Instead returned: " + returned);

		verify(this.loginRepo).findByUsername(this.username);
	}

	/**
	 * Tests {@link UserDetailsServiceImpl} 'findByUsername' method when the given
	 * {@link String} username does not match a corresponding {@link String}
	 * username in a {@link Login} object. Test asserts that a
	 * {@link UsernameNotFoundException} was thrown and verifies that the
	 * {@link LoginRepo} 'findByUsername' method was called.
	 */
	@Test
	void loadUserByUsernameTest_UsernameDoesNotExist() {
		when(this.loginRepo.findByUsername(this.username)).thenReturn(Optional.empty());

		assertThrows(UsernameNotFoundException.class, () -> this.userDetailsService.loadUserByUsername(this.username),
				"UserDetailsService.loadUserByUsername(" + this.username
						+ ") did not throw UsernameNotFoundException as expected.");

		verify(this.loginRepo).findByUsername(this.username);
	}

	/**
	 * Tests {@link UserDetailsServiceImpl} 'findByUsername' method when the given
	 * {@link String} username matches a corresponding {@link String} username in a
	 * {@link Login} object and the {@link List}<{@link GrantedAuthority}> in the
	 * {@link UserDetails} object created from the {@link Login} object is empty.
	 * Test asserts that a {@link UsernameNotFoundException} was thrown and verifies
	 * that the {@link LoginRepo} 'findByUsername' method was called.
	 */
	@Test
	void loadUserByUsernameTest_UserHasNoGrantedAuthorities() {

		optionalLogin.get().setRoles("");
		when(this.loginRepo.findByUsername(this.username)).thenReturn(this.optionalLogin);

		assertThrows(UsernameNotFoundException.class, () -> this.userDetailsService.loadUserByUsername(this.username),
				"UserDetailsService.loadUserByUsername(" + this.username
						+ ") did not throw UsernameNotFoundException as expected.");

		verify(this.loginRepo).findByUsername(this.username);
	}
}
