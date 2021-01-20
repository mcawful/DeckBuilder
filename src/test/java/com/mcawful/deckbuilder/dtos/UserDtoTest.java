/**
 * 
 */
package com.mcawful.deckbuilder.dtos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.mcawful.deckbuilder.exceptions.MalformattedEmailException;
import com.mcawful.deckbuilder.exceptions.MalformattedUsernameException;

/**
 * @author Michael McAuliffe
 *
 */
@SpringBootTest
class UserDtoTest {

	private UserDto userDto;

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
		userDto = new UserDto();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Tests the 'setUsername' method of the {@link UserDto} object when passed a
	 * validly formatted username. Test asserts equals that the expected username
	 * field matches the actual username field of the {@link UserDto} object and
	 * fails if a {@link MalformattedUsernameException} is thrown.
	 */
	@Test
	void setUsernameTest_VaildUsername() {

		String valid = "Valid_Username";

		try {
			userDto.setUsername(valid);
			assertEquals(valid, userDto.getUsername(), "Username was supposed to be set to '" + valid + "' but was '"
					+ userDto.getUsername() + "' instead.");
		} catch (MalformattedUsernameException e) {
			e.printStackTrace();
			fail("A MalformattedUsernameException was thrown unexpectedly.");
		}
	}

	/**
	 * Tests the 'setUsername' method of the {@link UserDto} object when passed an
	 * invalidly formatted username. Test asserts that a
	 * {@link MalformattedUsernameException} is thrown.
	 */
	@Test
	void setUsernameTest_InvaildUsername() {

		String invaild = "_Invaild Username.";

		assertThrows(MalformattedUsernameException.class, () -> userDto.setUsername(invaild));
	}

	/**
	 * Tests the 'setEmail' method of the {@link UserDto} object when passed a
	 * validly formatted email. Test asserts equals that the expected email field
	 * matches the actual email field of the {@link UserDto} object and fails if a
	 * {@link MalformattedEmailException} is thrown.
	 */
	@Test
	void setEmailTest_VaildEmail() {

		String valid = "valid@email.com";

		try {
			userDto.setEmail(valid);
			assertEquals(valid, userDto.getEmail(),
					"Email was supposed to be set to '" + valid + "' but was '" + userDto.getEmail() + "' instead.");
		} catch (MalformattedEmailException e) {
			e.printStackTrace();
			fail("A MalformattedEmailException was thrown unexpectedly.");
		}
	}

	/**
	 * Tests the 'setEmail' method of the {@link UserDto} object when passed an
	 * invalidly formatted email. Test asserts that a
	 * {@link MalformattedEmailException} is thrown.
	 */
	@Test
	void setEmailTest_InvaildEmail() {

		String invaild = "Invaild Email";

		assertThrows(MalformattedEmailException.class, () -> userDto.setEmail(invaild));
	}
}
