/**
 * 
 */
package com.mcawful.deckbuilder.models;

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
class UserTest {

	User user;

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
		user = new User();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * 
	 */
	@Test
	void setUsernameTest_VaildUsername() {

		String valid = "Valid_Username";

		try {
			user.setUsername(valid);
			assertEquals(valid, user.getUsername(),
					"Username was supposed to be set to '" + valid + "' but was '" + user.getUsername() + "' instead.");
		} catch (MalformattedUsernameException e) {
			e.printStackTrace();
			fail("A MalformattedUsernameException was thrown unexpectedly.");
		}
	}

	/**
	 * 
	 */
	@Test
	void setUsernameTest_InvaildUsername() {

		String invaild = "_Invaild Username.";

		assertThrows(MalformattedUsernameException.class, () -> user.setUsername(invaild));
	}

	/**
	 * 
	 */
	@Test
	void setEmailTest_VaildEmail() {

		String valid = "valid@email.com";

		try {
			user.setEmail(valid);
			assertEquals(valid, user.getEmail(),
					"Email was supposed to be set to '" + valid + "' but was '" + user.getEmail() + "' instead.");
		} catch (MalformattedEmailException e) {
			e.printStackTrace();
			fail("A MalformattedEmailException was thrown unexpectedly.");
		}
	}

	/**
	 * 
	 */
	@Test
	void setEmailTest_InvaildEmail() {

		String invaild = "Invaild Email";

		assertThrows(MalformattedEmailException.class, () -> user.setEmail(invaild));
	}
}
