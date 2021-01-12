/**
 * 
 */
package com.mcawful.deckbuilder.services;

import java.util.List;

import com.mcawful.deckbuilder.models.User;

/**
 * @author linkm
 *
 */
public interface UserService {

	public User getUser(int id);
	public User createUser(User user);
	public User updateUser(User user, int id);
	public User deleteUser(int id);
	public List<User> getAllUsers();
	
}
