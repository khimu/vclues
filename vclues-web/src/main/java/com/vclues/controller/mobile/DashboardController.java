package com.vclues.controller.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vclues.controller.BaseController;
import com.vclues.core.entity.User;
import com.vclues.core.service.IGameService;
import com.vclues.core.service.IStoryService;
import com.vclues.core.service.IUserService;

@Controller("mobileDashboardController")
@RequestMapping(value = {"/mobile"})
public class DashboardController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IStoryService storyService;
    
    @Autowired
    private IGameService gameService;


    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String index(Model model) {
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
    	
    	logger.info("mobile going to login");
    	
    	/* From registar */
    	User user = new User();
        model.addAttribute("userForm", user);
        //model.addAttribute("content", "signup"); 
    	 
    	model.addAttribute("content", "login");
        //return "index";
    	return "mobile/login";
    }

    /* TEMPORARY */
    
    //@RequestMapping(value = {"/"})
    
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(Model model) {
    	return "mobile/home";
    }
 
    @RequestMapping(value = {"/cast"}, method = RequestMethod.GET)
    public String cast(Model model) {
    	return "mobile/cast";
    }

    @RequestMapping(value = {"/players"}, method = RequestMethod.GET)
    public String players(Model model) {
    	return "mobile/players";
    }
    
    @RequestMapping(value = {"/videos"}, method = RequestMethod.GET)
    public String videos(Model model) {
    	return "mobile/videos";
    }    
}
