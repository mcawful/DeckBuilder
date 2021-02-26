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
import org.springframework.dao.DataIntegrityViolationException;
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
	 * when passed a <code>null</code> object. Test verifies that the
	 * {@link LoginRepo} 'save' method is called and asserts that an
	 * {@link IllegalArgumentException} is thrown.
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
	 * Tests the 'createOrUpdateLogin' method of the {@link LoginServiceImpl} class
	 * when a {@link Login} object is passed in with a {@link String} username that
	 * already exists. Test verifies that the {@link LoginRepo} 'save' method is
	 * called and asserts that a {@link DataIntegrityViolationException} is thrown.
	 * 
	 * @throws Exception
	 */
	@Test
	void createOrUpdateLoginTest_LoginUsernameAlreadyExists() throws Exception {

		when(this.loginRepo.save(this.login)).thenThrow(DataIntegrityViolationException.class);

		assertThrows(DataIntegrityViolationException.class, () -> this.loginService.createOrUpdateLogin(this.login),
				"LoginServiceImpl.createOrUpdateLogin(" + this.login
						+ ") did not throw an 'DataIntegrityViolationException' as expected.");

		verify(this.loginRepo).save(this.login);
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
	 * Tests the 'deleteLogin' method of the {@link LoginServiceImpl} when passed a
	 * <code>null</code>. Test asserts that an {@link IllegalArgumentException} was
	 * thrown and verifies that the {@link LoginRepo} 'deleteByUsername' method was
	 * called.
	 * 
	 * @throws Exception
	 */
	@Test
	void deleteLoginTest_PassedNullValue() throws Exception {

		doThrow(IllegalArgumentException.class).when(this.loginRepo).deleteByUsername(null);

		assertThrows(IllegalArgumentException.class, () -> this.loginService.deleteLogin(null),
				"LoginServiceImpl.deleteLogin(" + null + ") did not throw a 'IllegalArgumentException' as expected.");

		verify(this.loginRepo).deleteByUsername(null);
	}

	/**
	 * Tests the 'deleteLogin' method of the {@link LoginServiceImpl} when passed an
	 * non existent {@link String} username in a {@link Login} object. Test asserts
	 * that an {@link EmptyResultDataAccessException} was thrown and verifies that
	 * the {@link LoginRepo} 'deleteByUsername' method was called.
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
