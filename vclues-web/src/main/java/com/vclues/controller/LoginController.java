package com.vclues.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vclues.core.entity.User;
import com.vclues.core.security.ISecurityService;
import com.vclues.core.service.IGameService;
import com.vclues.core.service.IStoryService;
import com.vclues.core.service.IUserService;

@Controller
public class LoginController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IStoryService storyService;
    
    @Autowired
    private IGameService gameService;
    
    @Autowired
    private ISecurityService securityService;

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {    
    	User user = getLoggedInUser();
		
    	if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}		

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	if (!(auth instanceof AnonymousAuthenticationToken)) {
    		if(auth.getAuthorities().contains("ROLE_ADMIN")) {
    	    	model.addAttribute("stories", storyService.getAllStories());
    	    	model.addAttribute("content", "welcome"); 
    	    	model.addAttribute("title", "Dashboard");
    			return "admin";
    		}

    	}		
    	
		return "redirect:/";
    }
    
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(@RequestHeader("User-Agent") String userAgent, Model model) {
    	
    	logger.info(userAgent);
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	if (!(auth instanceof AnonymousAuthenticationToken)) {
    		model.addAttribute("stories", storyService.getAllStories());
    		model.addAttribute("content", "welcome");
    		model.addAttribute("title", "Dashboard");
    		/*
    		if(auth.getAuthorities().contains("ROLE_ADMIN")) {
    			return "admin";
    		}
    		*/

    		//model.addAttribute("games", gameService.findGamesByEmail(getLoggedInUser().getEmail()));
    		
    		model.addAttribute("gameCount", gameService.countByEmail(getLoggedInUser().getEmail()));
    		
    		System.out.println("gameCount " + model.toString());

    		return "menu";
    	}

    	logger.info("going to login");
    	
    	/* From registar */
    	User user = new User();
        model.addAttribute("userForm", user);

    	model.addAttribute("content", "login");

    	return "login";
    }
    

    @PostMapping(value = {"/fblogin"})
    public String login(@RequestParam String fbId, @RequestParam String accessToken, @RequestParam String email, Model model) {

    	if(StringUtils.trimToNull(email) !=  null) {
    		User user = userService.autoSaveFacebookLoginUsers(email.toLowerCase().trim(), accessToken+fbId);
    		securityService.facebookAutoLogin(user, accessToken+fbId);
    	}

    	return "redirect:/";
    }

}
