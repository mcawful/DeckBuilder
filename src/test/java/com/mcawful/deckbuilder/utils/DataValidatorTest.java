/**
 * 
 */
package com.mcawful.deckbuilder.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Michael McAuliffe
 *
 */
@SpringBootTest
class DataValidatorTest {

	private DataValidator dataValidator;

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

		dataValidator = new DataValidator();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Tests the 'isUsernameValid' method of the {@link DataValidator} class when
	 * passed a validly formatted username. Test asserts true when passed a valid
	 * username.
	 */
	@Test
	void isUsernameValidTest_VaildUsername() {

		String valid = "Valid_Username";

		boolean result = dataValidator.isUsernameValid(valid);

		assertTrue(result, "Expected the result to be true.");
	}

	/**
	 * Tests the 'isUsernameValid' method of the {@link DataValidator} class when
	 * passed an invalidly formatted username. Test asserts false when passed an
	 * invalid username.
	 */
	@Test
	void isUsernameValidTest_InvaildUsername() {

		String invaild = "_Invaild Username.";

		boolean result = dataValidator.isUsernameValid(invaild);

		assertFalse(result, "Expected the result to be false.");
	}

	/**
	 * Tests the 'isEmailValid' method of the {@link DataValidator} class when
	 * passed a validly formatted email. Test asserts true when passed a valid
	 * email.
	 */
	@Test
	void isEmailValidTest_VaildEmail() {

		String valid = "valid@email.com";

		boolean result = dataValidator.isEmailValid(valid);

		assertTrue(result, "Expected the result to be true.");
	}

	/**
	 * Tests the 'isEmailValid' method of the {@link DataValidator} class when
	 * passed an invalidly formatted email. Test asserts false when passed an
	 * invalid email.
	 */
	@Test
	void isEmailValidTest_InvaildEmail() {

		String invaild = "Invaild Email";

		boolean result = dataValidator.isEmailValid(invaild);

		assertFalse(result, "Expected the result to be false.");

	}
}
