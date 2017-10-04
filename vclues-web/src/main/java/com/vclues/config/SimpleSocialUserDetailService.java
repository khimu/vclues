package com.vclues.config;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import com.vclues.core.entity.User;
import com.vclues.core.security.ISecurityService;
import com.vclues.core.service.IGameService;
import com.vclues.core.service.IStoryService;
import com.vclues.core.service.IUserService;

/**
 * This class delegates requests forward to our UserDetailsService implementation.
 * This is possible because we use the username of the user as the account ID.
 * @author Petri Kainulainen
 */
// disable
public class SimpleSocialUserDetailService implements SocialUserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSocialUserDetailService.class);

    private UserDetailsService userDetailsService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private ISecurityService securityService;

    public SimpleSocialUserDetailService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Loads the username by using the account ID of the user.
     * @param userId    The account ID of the requested user.
     * @return  The information of the requested user.
     * @throws UsernameNotFoundException    Thrown if no user is found.
     * @throws DataAccessException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
        LOGGER.info("Loading user by user id: {}", userId);
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        
        if(StringUtils.trimToNull(userId) !=  null) {
    		String password = RandomStringUtils.randomAlphabetic(20);
    		User user = userService.autoSaveFacebookLoginUsers(userId.toLowerCase().trim(), password);
    		
    		// password is ignored
    		securityService.facebookAutoLogin(user, password);
    	}
        
        LOGGER.info("Found user details: {}", userDetails);

        return (SocialUserDetails) userDetails;
    }
}
