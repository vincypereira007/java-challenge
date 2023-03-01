package jp.co.axa.apidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.axa.apidemo.entities.User;

/**
 * The Interface UserRepository.
 */
public interface UserRepository extends JpaRepository<User,Long> {
	
	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	User findByUsername(String username);
}
