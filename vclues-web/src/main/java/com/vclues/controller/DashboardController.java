package com.vclues.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vclues.core.app.Constant;
import com.vclues.core.data.Game;
import com.vclues.core.data.Player;
import com.vclues.core.entity.User;
import com.vclues.core.service.IGameService;
import com.vclues.core.service.IStoryService;
import com.vclues.core.service.IUserService;

@Controller
public class DashboardController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IStoryService storyService;
    
    @Autowired
    private IGameService gameService;

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {    
    	User user = getLoggedInUser();
		
    	if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}		

    	model.addAttribute("stories", storyService.getAllStories());
    	model.addAttribute("content", "welcome"); 
    	model.addAttribute("title", "Dashboard");
    	return "internal";
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

    		model.addAttribute("game", gameService.getCurrentGame(getLoggedInUser().getId()));

    		return "menu";
    	}

    	logger.info("going to login");
    	
    	/* From registar */
    	User user = new User();
        model.addAttribute("userForm", user);

    	model.addAttribute("content", "login");

    	return "login";
    }

}
