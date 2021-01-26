/**
 * 
 */
package com.mcawful.deckbuilder.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.mcawful.deckbuilder.aspects.GlobalControllerExceptionHandler;
import com.mcawful.deckbuilder.dtos.UserDto;
import com.mcawful.deckbuilder.models.User;
import com.mcawful.deckbuilder.services.UserService;

/**
 * @author Michael McAuliffe
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerMvcTest {

	@Autowired
	private UserControllerMvc userController;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	private String baseUri = "/user";

	private UserDto userDto;

	private User user;

	private String userJson;

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

		this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController)
				.setControllerAdvice(GlobalControllerExceptionHandler.class).build();

		this.user = new User(0, "TestUsername", "test@email.com", LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS),
				true);
		this.userDto = new UserDto(this.user);
		this.userJson = new ObjectMapper().writeValueAsString(this.userDto);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test asserts that the {@link UserControllerMvc} has been instantiated.
	 * 
	 * @throws Exception
	 */
	@Test
	void contextLoads() throws Exception {
		assertThat(this.userController).isNotNull();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void getUserTest_UserExists() throws Exception {

		when(this.userService.getUser(this.user.getId())).thenReturn(this.user);

		RequestBuilder request = MockMvcRequestBuilders.get(this.baseUri + "/" + this.user.getId());

		this.mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(this.userJson));

		verify(this.userService).getUser(this.user.getId());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void getUserTest_UserDoesNotExist() throws Exception {

		when(this.userService.getUser(0)).thenThrow(NoSuchElementException.class);

		RequestBuilder request = MockMvcRequestBuilders.get(this.baseUri + "/0");

		this.mockMvc.perform(request).andExpect(status().isNotFound());

		verify(this.userService).getUser(0);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void createUserTest_SuccessfulCreate() throws Exception {

		when(this.userService.createOrUpdateUser(this.user)).thenReturn(this.user);

		RequestBuilder request = MockMvcRequestBuilders.post(this.baseUri).content(this.userJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE);

		this.mockMvc.perform(request).andExpect(status().isCreated());

		verify(this.userService).createOrUpdateUser(this.user);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void createUserTest_MalformattedUsername() throws Exception {

		this.userDto.setUsername("_Invaild Username.");
		this.userJson = new ObjectMapper().writeValueAsString(this.userDto);

		RequestBuilder request = MockMvcRequestBuilders.post(this.baseUri).content(this.userJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE);

		this.mockMvc.perform(request).andExpect(status().isBadRequest());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void createUserTest_MalformattedEmail() throws Exception {

		this.userDto.setEmail("Invaild Email");
		this.userJson = new ObjectMapper().writeValueAsString(this.userDto);

		RequestBuilder request = MockMvcRequestBuilders.post(this.baseUri).content(this.userJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE);

		this.mockMvc.perform(request).andExpect(status().isBadRequest());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void createUserTest_DataConstraintViolation() throws Exception {

		when(this.userService.createOrUpdateUser(this.user)).thenThrow(DataIntegrityViolationException.class);

		RequestBuilder request = MockMvcRequestBuilders.post(this.baseUri).content(this.userJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE);

		this.mockMvc.perform(request).andExpect(status().isConflict());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void updateUserTest_SuccessfulUpdate() throws Exception {

		when(this.userService.getUser(this.user.getId())).thenReturn(this.user);
		when(this.userService.createOrUpdateUser(this.user)).thenReturn(this.user);

		RequestBuilder request = MockMvcRequestBuilders.put(this.baseUri + "/" + this.user.getId())
				.content(this.userJson).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE);

		this.mockMvc.perform(request).andExpect(status().isOk());

		verify(this.userService).getUser(this.user.getId());
		verify(this.userService).createOrUpdateUser(this.user);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void updateUserTest_UserDoesNotExist() throws Exception {

		when(this.userService.getUser(this.user.getId())).thenThrow(NoSuchElementException.class);

		RequestBuilder request = MockMvcRequestBuilders.put(this.baseUri + "/" + this.user.getId())
				.content(this.userJson).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE);

		this.mockMvc.perform(request).andExpect(status().isBadRequest());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void updateUserTest_MalformattedUsername() throws Exception {

		this.userDto.setUsername("_Invaild Username.");
		this.userJson = new ObjectMapper().writeValueAsString(this.userDto);

		RequestBuilder request = MockMvcRequestBuilders.put(this.baseUri + "/" + this.user.getId())
				.content(this.userJson).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE);

		this.mockMvc.perform(request).andExpect(status().isBadRequest());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void updateUserTest_MalformattedEmail() throws Exception {

		this.userDto.setUsername("Invalid Email");
		this.userJson = new ObjectMapper().writeValueAsString(this.userDto);

		RequestBuilder request = MockMvcRequestBuilders.put(this.baseUri + "/" + this.user.getId())
				.content(this.userJson).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE);

		this.mockMvc.perform(request).andExpect(status().isBadRequest());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void updateUserTest_DataConstraintViolation() throws Exception {

		when(this.userService.createOrUpdateUser(this.user)).thenThrow(DataIntegrityViolationException.class);

		RequestBuilder request = MockMvcRequestBuilders.put(this.baseUri + "/" + this.user.getId())
				.content(this.userJson).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE);

		this.mockMvc.perform(request).andExpect(status().isConflict());

		verify(this.userService).createOrUpdateUser(this.user);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void deleteUserTest_SuccessfulDelete() throws Exception {

		doNothing().when(this.userService).deleteUser(this.user.getId());

		RequestBuilder request = MockMvcRequestBuilders.delete(this.baseUri + "/" + this.user.getId());

		this.mockMvc.perform(request).andExpect(status().isOk());

		verify(this.userService).deleteUser(this.user.getId());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void deleteUserTest_NoEnitityToDelete() throws Exception {
		// TODO: Find proper implementation for this test
		fail("Test not yet implemented.");
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	void getAllUserTest() throws Exception {

		List<User> userList = new ArrayList<User>();
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		List<UserDto> returnedList = null;

		userList.add(this.user);
		userDtoList.add(this.userDto);

		when(this.userService.getAllUsers()).thenReturn(userList);

		RequestBuilder request = MockMvcRequestBuilders.get(this.baseUri);

		MvcResult result = this.mockMvc.perform(request).andExpect(status().isOk()).andReturn();

		String responseBody = result.getResponse().getContentAsString();

		try {
			returnedList = new ObjectMapper().readValue(responseBody, new TypeReference<List<UserDto>>() {
			});
		} catch (MismatchedInputException e) {
			e.printStackTrace();
			fail("Returned JSON did not match expected object.");
		}

		verify(this.userService).getAllUsers();

		assertEquals(userDtoList, returnedList,
				"Expected '" + userDtoList + "' but got '" + returnedList + "' instead.");
	}
}
