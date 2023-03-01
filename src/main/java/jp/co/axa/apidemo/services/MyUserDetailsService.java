package jp.co.axa.apidemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.config.UserPrincipal;
import jp.co.axa.apidemo.entities.User;
import jp.co.axa.apidemo.repositories.UserRepository;

/**
 * The Class MyUserDetailsService.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}	
		
		return new UserPrincipal(user);
	}

}
