package com.vclues.core.security;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vclues.core.entity.Authority;
import com.vclues.core.entity.User;


@Service
public class SecurityService implements ISecurityService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private org.springframework.security.core.userdetails.UserDetailsService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(SecurityService.class);

	@Override
	public String findLoggedInUsername() {
		Object userDetails = SecurityContextHolder.getContext()
				.getAuthentication().getDetails();
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}

		return null;
	}

	@Override
	public void autologin(String username, String password) {
		UserDetails userDetails = userDetailsService
				.loadUserByUsername(username);
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, password, userDetails.getAuthorities());

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(
					usernamePasswordAuthenticationToken);
			logger.debug(String.format("Auto login %s successfully!", username));
		}
	}
	
	@Override
	public void facebookAutoLogin(User user, String facebookPassword) {
		UserDetails userDetails = this.loadFBUserByUsername(user, facebookPassword);
		
		logger.info("facebookPassword" + facebookPassword);
		
        logger.debug("Logging in principal: {}", userDetails);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.info("User: {} has been logged in.", userDetails);
	}	

    @Transactional(readOnly = true)
    public UserDetails loadFBUserByUsername(final User user, String facebookPassword) {
        logger.debug("Authenticating {}", user.getEmail());

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : user.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), facebookPassword, grantedAuthorities);

    }
	
}
