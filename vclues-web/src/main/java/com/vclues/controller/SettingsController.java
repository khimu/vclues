package com.vclues.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vclues.core.entity.User;

/**
 * Grant permission 
 * 
 * @author khimung
 *
 */
@Controller
@RequestMapping(value = "/settings")
public class SettingsController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(SettingsController.class);

	@GetMapping
	public String get(Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		// create groups
		// add features
		
		// billing
		// auto pay
		// turn on/off service
		
		
        model.addAttribute("content", "settings"); 
        return "internal";
	}
}
