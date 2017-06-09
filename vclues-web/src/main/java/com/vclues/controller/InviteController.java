package com.vclues.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vclues.core.entity.User;
import com.vclues.core.service.IGameService;
import com.vclues.core.service.IStoryService;
import com.vclues.core.service.IUserService;

@Controller
@RequestMapping(value = "/invite")
public class InviteController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(InviteController.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IStoryService storyService;
	
	@Autowired
	private IGameService gameService;


    
    @GetMapping("/{gameId}")
    public String inviteForm(@PathVariable("gameId") String gameId, Model model) {
    	logger.info("In add game");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		model.addAttribute("gameId", gameId);
        model.addAttribute("content", "invitePlayers");
        model.addAttribute("title", "Invite Players");
        
		// redirect back to list
        return "internal";
    }
    
    /*
     * send email to invite friends
     * create a user account with email and assign a temporary password
     * 
     * Use "@RequestParam("myparam") List<String> params" for same param names
     */
    @PostMapping 
    public String inviteSubmit(@RequestParam Map<String,String> requestParams) {
    	logger.info("In add game");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		String emails = requestParams.get("emails");
		String gameId = requestParams.get("gameId");

		// send email
		// create user with password
		// send email for each invite
		
		gameService.sendInviteEmail(user.getEmail(), emails);

		// display the game gameDetail page
		return "redirect:/game/" + gameId;
    }
}
