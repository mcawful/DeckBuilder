/**
 * 
 */
package com.mcawful.deckbuilder.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;

import com.mcawful.deckbuilder.daos.Login;
import com.mcawful.deckbuilder.repos.LoginRepo;

/**
 * @author Michael McAuliffe
 *
 */
@SpringBootTest
class LoginServiceImplTest {

	@Autowired
	private LoginServiceImpl loginService;

	@MockBean
	private LoginRepo loginRepo;

	private Login login;

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

		this.login = new Login("ADMIN", "admin", "password");
		this.username = login.getUsername();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Tests the {@link LoginServiceImpl} 'createOrUpdateLogin' method when passed a
	 * {@link Login} object. Asserts that the returns {@link Login} object equals
	 * what is expected and verifies that the {@link LoginRepo} 'save' method was
	 * called.
	 * 
	 * @throws Exception
	 */
	@Test
	void createOrUpdateLoginTest_Successful() throws Exception {

		when(this.loginRepo.save(this.login)).thenReturn(this.login);

		Login returned = this.loginService.createOrUpdateLogin(this.login);

		assertEquals(this.login, returned);

		verify(this.loginRepo).save(this.login);
	}

	/**
	 * Tests the 'createOrUpdateLogin' method of the {@link LoginServiceImpl} class
	 * when a null {@link Login} object is passed in. Test verifies that the
	 * {@link LoginRepo} 'save' method is called and asserts that an
	 * 'IllegalArgumentException' exception is thrown.
	 * 
	 * @throws Exception
	 */
	@Test
	void createOrUpdateLoginTest_LoginObjectIsNull() throws Exception {

		when(this.loginRepo.save(null)).thenThrow(IllegalArgumentException.class);

		assertThrows(IllegalArgumentException.class, () -> this.loginService.createOrUpdateLogin(null),
				"LoginServiceImpl.createOrUpdateLogin(null) did not throw an 'IllegalArgumentException' as expected.");

		verify(this.loginRepo).save(null);
	}

	/**
	 * Tests the 'deleteLogin' method of the {@link LoginServiceImpl} when passed an
	 * existing {@link String} username in a {@link Login} object. Test verifies
	 * that the {@link LoginRepo} 'deleteByUsername' method was called.
	 * 
	 * @throws Exception
	 */
	@Test
	void deleteLoginTest_Successful() throws Exception {

		this.loginService.deleteLogin(this.username);

		verify(this.loginRepo).deleteByUsername(this.username);
	}

	/**
	 * Tests the 'deleteLogin' method of the {@link LoginServiceImpl} when passed an
	 * non existent {@link String} username in a {@link Login} object. Test asserts
	 * that a 'EmptyResultDataAccessException' was thrown and verifies that the
	 * {@link LoginRepo} 'deleteByUsername' method was called.
	 * 
	 * @throws Exception
	 */
	@Test
	void deleteLoginTest_LoginDoesNotExist() throws Exception {

		doThrow(EmptyResultDataAccessException.class).when(this.loginRepo).deleteByUsername(this.username);

		assertThrows(EmptyResultDataAccessException.class, () -> this.loginService.deleteLogin(this.username),
				"LoginServiceImpl.deleteLogin(" + this.username
						+ ") did not throw a 'EmptyResultDataAccessException' as expected.");

		verify(this.loginRepo).deleteByUsername(this.username);
	}
}
