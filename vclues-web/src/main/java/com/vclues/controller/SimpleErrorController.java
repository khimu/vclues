package com.vclues.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
public class SimpleErrorController implements ErrorController {

	private static final String PATH = "/error";


	@Override
	public String getErrorPath() {
		return PATH;
	}
	
	/*
    @RequestMapping(value = {"/error"}, method = RequestMethod.GET)
    public String error(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	if (!(auth instanceof AnonymousAuthenticationToken)) {
    		model.addAttribute("content", "error");
    		return "internal";
    	}
    	
		model.addAttribute("content", "error");
		return "index";
    }
    */
}