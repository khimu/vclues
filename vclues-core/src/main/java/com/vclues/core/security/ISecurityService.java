package com.vclues.core.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public interface ISecurityService {

	public String findLoggedInUsername();

	public void autologin(String username, String password);

}
