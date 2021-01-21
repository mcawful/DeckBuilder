/**
 * 
 */
package com.mcawful.deckbuilder.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
 * Unit tests for the {@link UserServiceImpl} class methods.
 * 
 * @author Michael McAuliffe
 *
 */
@SpringBootTest
class UserServiceImplTest {

	@Autowired
	private UserServiceImpl userService;

	@MockBean
	private UserRepo userRepo;

	private Optional<User> user;

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
		this.user = Optional
				.of(new User(1, "TestName", "test@mail.com", LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS), true));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		this.userRepo.deleteAll();
	}

	/**
	 * Test asserts that the {@link UserServiceImpl} has been instantiated.
	 * 
	 * @throws Exception When {@link UserServiceImpl} is null
	 */
	@Test
	void contextLoads() throws Exception {
		assertThat(this.userService).isNotNull();
	}

	/**
	 * Tests the 'getUser' method of the {@link UserServiceImpl} class when an ID
	 * for an existing {@link User} is passed in. Test verifies that the
	 * {@link UserRepo} 'findById' method is called and asserts equal that the
	 * returned {@link User} object matches what is expected.
	 */
	@Test
	void getUserTest_UserExists() throws Exception {

		when(this.userRepo.findById(this.user.get().getId())).thenReturn(this.user);

		User returnedUser = this.userService.getUser(this.user.get().getId());

		verify(this.userRepo).findById(this.user.get().getId());

		assertEquals(this.user.get(), returnedUser, "UserServiceImpl.getUser(" + this.user.get().getId()
				+ ") did not return expected User object. Instead returned: " + returnedUser);
	}

	/**
	 * Tests the 'getUser' method of the {@link UserServiceImpl} class when an ID
	 * for a non-existent {@link User} is passed in. Test verifies that the
	 * {@link UserRepo} 'findById' method is called and asserts that a
	 * 'NoSuchElementException' exception was thrown.
	 */
	@Test
	void getUserTest_UserDoesNotExist() throws Exception {

		when(this.userRepo.findById(0)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> this.userService.getUser(0),
				"UserServiceImpl.getUser(0) did not throw a 'NoSuchElementException' as expected.");

		verify(this.userRepo).findById(0);
	}

	/**
	 * Tests the 'createOrUpdateUser' method of the {@link UserServiceImpl} class
	 * when a valid {@link User} object is passed in. Test verifies that the
	 * {@link UserRepo} 'save' method is called and asserts equal that the returned
	 * {@link User} object matches what is expected.
	 */
	@Test
	void createOrUpdateUserTest_SuccessfulAddOrUpdateUser() throws Exception {

		when(this.userRepo.save(this.user.get())).thenReturn(this.user.get());

		User returnedUser = this.userService.createOrUpdateUser(this.user.get());

		verify(this.userRepo).save(this.user.get());

		assertEquals(this.user.get(), returnedUser, "UserServiceImpl.createOrUpdateUser(" + this.user.get().getId()
				+ ") did not return expected User object. Instead returned: " + returnedUser);
	}

	/**
	 * Tests the 'createOrUpdateUser' method of the {@link UserServiceImpl} class
	 * when a null {@link User} object is passed in. Test verifies that the
	 * {@link UserRepo} 'save' method is called and asserts that an
	 * 'IllegalArgumentException' exception is thrown.
	 */
	@Test
	void createOrUpdateUserTest_UserObjectIsNull() throws Exception {

		when(this.userRepo.save(null)).thenThrow(IllegalArgumentException.class);

		assertThrows(IllegalArgumentException.class, () -> this.userService.createOrUpdateUser(null),
				"UserServiceImpl.createUser(null) did not throw an 'IllegalArgumentException' as expected.");

		verify(this.userRepo).save(null);
	}

	/**
	 * Tests the 'deleteUser' method of the {@link UserServiceImpl} class when a
	 * non-null {@link User} id is passed in. Verifies that the {@link UserRepo}
	 * 'delete' method is called.
	 */
	@Test
	void deleteUserTest_NonNullUserId() throws Exception {

		this.userService.deleteUser(user.get().getId());

		verify(this.userRepo).deleteById(user.get().getId());
	}

	/**
	 * Tests the 'getAllUsers' method of the {@link UserServiceImpl} class. Verifies
	 * that the {@link UserRepo} 'findAll' method is called and that the returned
	 * {@link List}<{@link User}> matches what is expected.
	 */
	@Test
	void getAllUsersTest_Success() throws Exception {

		List<User> userList = new ArrayList<User>();
		userList.add(user.get());

		when(this.userRepo.findAll()).thenReturn(userList);

		List<User> returnedUserList = this.userService.getAllUsers();

		verify(this.userRepo).findAll();

		assertEquals(userList, returnedUserList,
				"UserServiceImpl.getAllUsers() did not return expected List of User objects. Expected: "
						+ userList.toString() + " Instead returned: " + returnedUserList.toString());
	}
}
