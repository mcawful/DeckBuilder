/**
 * 
 */
package com.mcawful.deckbuilder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mcawful.deckbuilder.daos.Login;
import com.mcawful.deckbuilder.repos.LoginRepo;

/**
 * @author Michael McAuliffe
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	private LoginRepo loginRepo;

	/**
	 * Autowires the {@link LoginRepo}.
	 * 
	 * @param loginRepo
	 */
	@Autowired
	public LoginServiceImpl(LoginRepo loginRepo) {
		super();
		this.loginRepo = loginRepo;
	}

	@Override
	public Login createOrUpdateLogin(Login login) throws IllegalArgumentException, DataIntegrityViolationException {

		return this.loginRepo.save(login);
	}

	@Override
	public void deleteLogin(String username) throws IllegalArgumentException, EmptyResultDataAccessException {

		this.loginRepo.deleteByUsername(username);
	}

}
