/**
 * 
 */
package com.mcawful.deckbuilder.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mcawful.deckbuilder.daos.Login;

/**
 * @author Michael McAuliffe
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public Login createOrUpdateLogin(Login login) throws IllegalArgumentException, DataIntegrityViolationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLogin(String username) {
		// TODO Auto-generated method stub

	}

}
