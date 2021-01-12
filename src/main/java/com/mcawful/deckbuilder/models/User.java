/**
 * 
 */
package com.mcawful.deckbuilder.models;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author linkm
 *
 */
@Data
public class User {

	private String username;
	private String email;
	private String password;
	private LocalDateTime accountCreatedDate;
	private boolean accountActive;
}
