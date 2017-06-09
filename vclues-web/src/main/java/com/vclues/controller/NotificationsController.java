package com.vclues.controller;

import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vclues.core.data.Player;
import com.vclues.core.entity.User;
import com.vclues.core.repository.StoryRepository;
import com.vclues.core.service.IGameService;
import com.vclues.core.service.IStoryService;
import com.vclues.core.service.IUserService;

@Controller
@RequestMapping(value = "/notifications")
public class NotificationsController extends BaseController {
		private final static Logger logger = LoggerFactory.getLogger(NotificationsController.class);
		
		@Autowired
		private StoryRepository businessRepository;
		
		@Autowired
		private IUserService userService;
		
		@Autowired
		private IStoryService storyService;
		
		@Autowired
		private IGameService gameService;
	    
		@GetMapping
		public String get(Model model) {
			User user = getLoggedInUser();
			if(user == null) {
				logger.info("Not able to retrieve user in session");
				return "redirect:/login";
			}
			
			List<Player> players = gameService.findAllCurrentGames(user.getId());
			
			//al_students.sort(Comparator.comparingInt(Student::getScore).reversed());
			
			model.addAttribute("players", players);
			
			model.addAttribute("user", user);
	        model.addAttribute("content", "notifications"); 
	        model.addAttribute("title", "Notifications");
	        
	        return "internal";
		}
		
}
