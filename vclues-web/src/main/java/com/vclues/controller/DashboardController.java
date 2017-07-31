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

    	Map<Integer, List<Game>> games = gameService.findAllUserGames(user.getId());

    	model.addAttribute("current", games.get(Constant.CURRENT));
    	model.addAttribute("past", games.get(Constant.PAST));
    	
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
    		if(auth.getAuthorities().contains("ROLE_ADMIN")) {
    			return "admin";
    		}
    		
    		return "internal";
    	}

    	logger.info("going to login");
    	
    	/* From registar */
    	User user = new User();
        model.addAttribute("userForm", user);
        //model.addAttribute("content", "signup"); 
    	 
    	model.addAttribute("content", "login");
        //return "index";
    	
    	if(userAgent.toLowerCase().contains("iphone") && userAgent.toLowerCase().contains("mobile")) {
    		return "mobile/login";
    	}
    	
    	return "login";
    }

    /* TEMPORARY */
    
    //@RequestMapping(value = {"/"})
    
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(Model model) {
    	return "home";
    }
 
    @RequestMapping(value = {"/cast"}, method = RequestMethod.GET)
    public String cast(Model model) {
    	return "cast";
    }

    @RequestMapping(value = {"/players"}, method = RequestMethod.GET)
    public String players(Model model) {
    	return "players";
    }
    
    @RequestMapping(value = {"/videos"}, method = RequestMethod.GET)
    public String videos(Model model) {
    	return "videos";
    }    
    
    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public String test(Model model) {
    	return "test";
    }
    
    @RequestMapping(value = {"/scripts"}, method = RequestMethod.GET)
    public String scripts(Model model) {
    	return "scripts";
    }    
    
    @RequestMapping(value = {"/clues"}, method = RequestMethod.GET)
    public String clues(Model model) {
    	return "clues";
    }
    
    @RequestMapping(value = {"/history"}, method = RequestMethod.GET)
    public String history(Model model) {
    	return "history";
    }
    
    @RequestMapping(value = {"/suspect"}, method = RequestMethod.GET)
    public String suspect(Model model) {
    	return "suspect";
    }
}
