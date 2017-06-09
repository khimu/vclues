package com.vclues.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vclues.core.data.Descriptor;
import com.vclues.core.entity.User;
import com.vclues.core.repository.StoryRepository;
import com.vclues.core.service.IUserService;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	private StoryRepository businessRepository;
	
	@Autowired
	private IUserService userService;
    
	@GetMapping
	public String get(Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		model.addAttribute("user", user);
        model.addAttribute("content", "profile"); 
        model.addAttribute("title", "Profile");
        
        return "internal";
	}
	
	@PostMapping
	public String put(@ModelAttribute("user") User profile, BindingResult bindingResult, Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		if(profile.getPassword() == null || "".equals(profile.getPassword())) {
			profile.setPassword(user.getPassword());
		}
		
		model.addAttribute("user", user);
        model.addAttribute("content", "profile"); 
        model.addAttribute("title", "Edit Profile");
        
        return "internal";
	}
	
	@DeleteMapping
	public String delete() {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		return "redirect:/logout";
	}

}
