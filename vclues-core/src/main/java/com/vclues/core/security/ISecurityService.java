package com.vclues.core.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.vclues.core.entity.User;

public interface ISecurityService {

	public String findLoggedInUsername();

	public void autologin(String username, String password);
	
	public void facebookAutoLogin(User user, String facebookPassword);

}
