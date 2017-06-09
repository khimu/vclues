package de.frontierpsychiatrist.example.oauth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.frontierpsychiatrist.example.oauth.domain.Credentials;
import de.frontierpsychiatrist.example.oauth.repository.CredentialsRepository;

/** 
 * @author Moritz Schulze
 */
@Service("jdbcUserDetailsService")
public class JdbcUserDetailsService implements UserDetailsService {
	
	@Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials credentials = credentialsRepository.findByName(username);
        if(credentials == null) {
            throw new UsernameNotFoundException("User " + username + " not found in database.");
        }
        return new User(credentials.getName(), credentials.getPassword(), credentials.isEnabled(), true, true, true, credentials.getAuthorities());
    }
}
