package com.vclues.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vclues.core.data.Game;
import com.vclues.core.entity.User;
import com.vclues.core.repository.StoryRepository;
import com.vclues.core.security.SecurityService;
import com.vclues.core.service.IGameService;
import com.vclues.core.service.IUserService;
import com.vclues.core.validator.DescriptorValidator;
import com.vclues.core.validator.UserValidator;

@Controller
public class RegistrationController extends BaseController {
	private final static Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
    @Autowired
    private IUserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private DescriptorValidator descriptorValidator;
    
    @Autowired
    private StoryRepository businessRepository;

    @Autowired
    private IGameService gameService;
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String registration(Model model) {
    	User user = new User();
    	
        model.addAttribute("userForm", user);
        model.addAttribute("content", "signup"); 
        return "index";
    }

    /**
     * Account registration
     * 
     * @param userForm
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/signup")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	StringBuilder builder = new StringBuilder();
        	logger.info("Binding error.  Saved user " + userForm.getEmail());
        	for(ObjectError error: bindingResult.getAllErrors()) {
        		builder.append(error.getDefaultMessage() + "\n");
        		logger.info("code " + error.getCode() + " " + error.getObjectName());
        	}
        	
        	model.addAttribute("error", builder.toString()); 
        	return "login";
        }

        userService.registerNewUser(userForm);
        
        model.addAttribute("content", "signup"); 
        model.addAttribute("message", "Thank you for registering.  Please check your email for the confirmation link.");

        if(getLoggedInUser() != null) {
    		
    		//model.addAttribute("games", gameService.findGamesByEmail(getLoggedInUser().getEmail()));
        	
        	model.addAttribute("gameCount", gameService.countByEmail(getLoggedInUser().getEmail()));
	
	        return "menu";
        }
        
        return "login";
    }
    
    /*
     * confirm email registration
     */
    @GetMapping("/confirm/{email}/{activationKey}")
    public String confirm(@PathVariable("email") String email, @PathVariable("activationKey") String activationKey, Model model) {
        
    	logger.info("Confirm user " + email + " " + activationKey);

        userService.confirmEmail(email, activationKey);
        
        // if activationKey has access to story
        // then if user exist create game 
        // create account, with activation key and default password using activationKey
        // send user email to confirm account creation

        // then check if user account already has activationKey and update story_access with user activationKey
        // create 

        model.addAttribute("message", "Thank you for confirming your email.  Please try logging in");
        model.addAttribute("content", "confirm"); 
    	
        return "redirect:/";
    }   
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(@RequestParam(value="message", required = false) String message, Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (message != null) {
        	logger.info("message " + message);
            model.addAttribute("message", "You have been logged out successfully.");

        	session().setAttribute("users", null);
        }
        
        if(logout != null) {
        	logger.info("logout " + logout);
            model.addAttribute("message", "You have been logged out successfully.");

            logout();
        }
    	
        User user = new User();
        model.addAttribute("userForm", user);
    	//model.addAttribute("content", "login"); 
        return "login";
    }
    
    @GetMapping("/forgotpassword")
    public String forgotpassword(Model model) {
        model.addAttribute("content", "forgotpassword"); 
        return "index";
    }

}
