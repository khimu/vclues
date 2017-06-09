package com.vclues.web.advice;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.vclues.core.entity.User;
import com.vclues.core.service.IUserService;

@ControllerAdvice
public class CurrentUserControllerAdvice {
	
	@Resource
	private IUserService userService;
	
    @ModelAttribute("currentUser")
    public User getCurrentUser(Authentication authentication) {
        UserDetails userDetails = (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
        if(userDetails == null) {
        	return null;
        }
        return userService.findByEmail(userDetails.getUsername());
    }
}
