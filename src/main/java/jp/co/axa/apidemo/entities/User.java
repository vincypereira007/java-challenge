package jp.co.axa.apidemo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class User.
 */
@Entity

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Instantiates a new user.
 *
 * @param id the id
 * @param username the username
 * @param password the password
 */
@AllArgsConstructor

/**
 * Instantiates a new user.
 */
@NoArgsConstructor
public class User {

	/** The id. */
	@Id
	private long id;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
}
