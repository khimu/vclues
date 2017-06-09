package com.vclues.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vclues.core.entity.User;
import com.vclues.core.service.IUserService;

public abstract class BaseController {
	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
    @Autowired
    protected IUserService userService;
    
	// A special header sent with each AJAX request
	protected static final String IS_AJAX_HEADER = "X-Requested-With=XMLHttpRequest";


	// example usage
	public HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

		return attr.getRequest().getSession(true); // true == allow create
	}
	
	public User getLoggedInUser() {
		User user = (User) session().getAttribute("loggedInUser");
		if(user == null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth == null) {
				return null;
			}
			String name = auth.getName(); //get logged in username
			String au = auth.getAuthorities().iterator().next().getAuthority();
			
			logger.info("logged in user name " + name  + " authority " + au);
			
			if(name != null) {
	    		user = userService.findByEmail(name);
	    		if(user != null) {
	    			logger.info("user is not null " + name);
	    			session().setAttribute("loggedInUser", user);
	    		}
			}
		}
		
		return user;
	}
}
