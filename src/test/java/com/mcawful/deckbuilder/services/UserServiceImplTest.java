/**
 * 
 */
package com.mcawful.deckbuilder.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mcawful.deckbuilder.models.User;
import com.mcawful.deckbuilder.repos.UserRepo;

/**
 * @author Michael McAuliffe
 *
 */
@SpringBootTest
class UserServiceImplTest {

	@Autowired
	private UserServiceImpl userService;

	@MockBean
	private UserRepo userRepo;

	private User user;

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
		user = new User(1, "TestName", "test@mail.com", LocalDateTime.now(), true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Tests the 'getUser' method of the {@link UserServiceImpl} when an ID for an
	 * existing {@link User} is passed in. Test verifies that the {@link UserRepo}
	 * 'getOne' method is called and asserts equal that the returned {@link User}
	 * object matches what is expected.
	 */
	@Test
	void getUserTest_UserExists() {

		when(userRepo.getOne(user.getId())).thenReturn(user);

		User returnedUser = userService.getUser(user.getId());

		verify(userRepo).getOne(user.getId());

		assertEquals(user, returnedUser, "UserServiceImpl.getUser(" + user.getId()
				+ ") did not return expected User object. Instead returned: " + returnedUser);
	}

	/**
	 * Tests the 'getUser' method of the {@link UserServiceImpl} when an ID for a
	 * non-existent {@link User} is passed in. Test verifies that the
	 * {@link UserRepo} 'getOne' method is called and asserts equal that the
	 * returned {@link User} object is null.
	 */
	@Test
	void getUserTest_UserDoesNotExist() {

		when(userRepo.getOne(0)).thenReturn(null);

		User returnedUser = userService.getUser(0);

		verify(userRepo).getOne(0);

		assertEquals(null, returnedUser,
				"UserServiceImpl.getUser(0) did not return a null User object. Instead returned: " + returnedUser);
	}
}
