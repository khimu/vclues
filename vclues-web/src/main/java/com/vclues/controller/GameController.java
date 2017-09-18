package com.vclues.controller;

import java.util.List;

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

import com.vclues.core.data.Announcement;
import com.vclues.core.data.Game;
import com.vclues.core.data.Player;
import com.vclues.core.entity.Hint;
import com.vclues.core.entity.Story;
import com.vclues.core.entity.User;
import com.vclues.core.enums.Frequency;
import com.vclues.core.service.IGameService;
import com.vclues.core.service.IStoryService;
import com.vclues.core.service.IUserService;

/**
 * TODO
 * Prevent editing of games that do not belong to the logged in user
 * 
 * Edit is disabled to start
 * 
 * TODO
 * Add a start game button
 * 
 * @author khimung
 *
 */
@Controller
@RequestMapping(value = "/game")
public class GameController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IStoryService storyService;
	
	@Autowired
	private IGameService gameService;
	
    @GetMapping("/gamemenu/{gameId}")
    public String gamemenu(@PathVariable("gameId") String gameId, Model model) {
    	logger.info("User guesses " + gameId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		Game game = gameService.getGameOnly(gameId);
		
		/*
		 * check if user is part of this game
		 */
		if(!game.getEmails().contains(user.getEmail())) {
			return "redirect:/game/all";
		}

		model.addAttribute("game", game);
        
		return "submenu";
    }      	

    /*
     * Called from murderer.html
     * 
     * user has selected a murderer
     */
    @GetMapping("/announcements/{gameId}")
    public String displayGameAnnouncments(@PathVariable("gameId") String gameId, Model model) {
    	logger.info("choose murderer");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		Game game = gameService.getGameOnly(gameId);
		
		/*
		 * check if user is part of this game
		 */
		if(!game.getEmails().contains(user.getEmail())) {
			return "redirect:/game/all";
		}

		List<Announcement> announcements = gameService.getAllGameAnnouncements(gameId);

		model.addAttribute("announcements", announcements);
		model.addAttribute("gameId", gameId);

		return "announcements";
    }  

    /*
     * Server cast.html (need to choose a character), castOnly.html (character already chosen), or murderer.html (need to choose a murderer)
     * Display cast selection and description when game has been chosen
     */
    @GetMapping("/cast/{id}")
    public String cast(@PathVariable("id") String gameId, Model model) {
    	logger.info("View game " + gameId);
    	
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		if(gameId == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/welcome";
		}
		
		Game game = gameService.getGameWithGameCast(gameId);
		
		/*
		 * check if user is part of this game
		 * but not necessary here - extra safe
		 */
		if(!game.getEmails().contains(user.getEmail())) {
			return "redirect:/game/all";
		}
		
		if(game == null) {
			logger.info("game is null for " + user.getId());
		}

		model.addAttribute("casts", game.getGameCast());
		
		Player player = gameService.findPlayerByUserIdAndGameId(user.getId(), gameId);
		
		model.addAttribute("player", player);
        
		if(player.getCastId() == null) {
			return "cast";
		}
		
		if(player.getMurdererId() == null) {
			logger.info("player id = " + player.getId());
			return "murderer";
		}
		
		logger.info("murderer is not null " + player.getMurdererId());
		return "castOnly";
    }
    
    /*
     * Called from murderer.html
     * 
     * user has selected a murderer
     */
    @GetMapping("/murderer/{gameId}/{castId}/{castName}")
    public String murderer(@PathVariable("gameId") String gameId, @PathVariable("castId") String castId, @PathVariable("castName") String castName, Model model) {
    	logger.info("choose murderer");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		Game game = gameService.getGameWithGameCast(gameId);

		model.addAttribute("casts", game.getGameCast());
		
		Player player = gameService.findPlayerByUserIdAndGameId(user.getId(), gameId);
		player.setMurdererId(Long.parseLong(castId));
		
		gameService.savePlayer(player);
		
		logger.info("murderer saved with " + castId);

		model.addAttribute("player", player);
		
		logger.info("murderer id " + player.getMurdererId());
        
		return "redirect:/game/cast/" + gameId;
		//return "cast";
    }  

    /*
     * Called from games.html
     * 
     * Stop the game before its over
     */
    @GetMapping("/end/{gameId}")
    public String end(@PathVariable("gameId") String gameId, Model model) {
    	logger.info("end game");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		Game game = gameService.getGameWithGameCast(gameId);

		/*
		 * check if user is part of this game
		 */
		if(!game.getEmails().contains(user.getEmail())) {
			return "redirect:/game/all";
		}		
		
		game.setDone(true);
		gameService.saveGame(game);

		model.addAttribute("gameCount", gameService.countByEmail(getLoggedInUser().getEmail()));
        
		return "menu";
    }  
    
    
    /*
     * Called from cast.html
     * 
     * user has chosen a character and now we're saving the player information
     */
    @GetMapping("/cast/{gameId}/{castId}/{castName}")
    public String addCast(@PathVariable("gameId") String gameId, @PathVariable("castId") String castId, @PathVariable("castName") String castName, Model model) {
    	logger.info("User choose a game " + gameId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		gameService.saveCastForGame(gameId, castId, user.getId(), castName, user.getEmail());
        
		//return "cast";
		return "redirect:/game/cast/" + gameId;
    }    
    
    /*
     * Serves guesses.html
     * 
     * show all players guesses at end of game
     */
    @GetMapping("/guess/{gameId}")
    public String guess(@PathVariable("gameId") String gameId, Model model) {
    	logger.info("User guesses " + gameId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		Game game = gameService.getGameOnly(gameId);
		
		/*
		 * check if user is part of this game
		 */
		if(!game.getEmails().contains(user.getEmail())) {
			return "redirect:/game/all";
		}

		List<Player> players = gameService.getGuesses(gameId);

		model.addAttribute("players", players);
        
		return "guesses";
    }        
    
    /*
     * Serves addGame.html
     * 
     * User has selected a story to play
     * show form for game information
     */
    @GetMapping("/select/{storyId}")
    public String showForm(@PathVariable("storyId") String storyId, Model model) {
    	logger.info("User selected a game");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		Story story = storyService.getStory(Long.parseLong(storyId));
		
		Game game = new Game();
		game.setCount(story.getSize());
		game.setName(story.getTitle());
		model.addAttribute("game", game);
		
		model.addAttribute("storyId", storyId);
		model.addAttribute("frequency", Frequency.values());
        model.addAttribute("content", "addGame");
        model.addAttribute("title", "Add Game");
        
        return "addGame";
    } 
    
    /*
     * Called from addGame.html
     * 
     * Game selection form submit
     */
    @PostMapping("/select/{storyId}")
    public String submitForm(@PathVariable("storyId") String storyId, @ModelAttribute("Game") Game game, BindingResult bindingResult, Model model) {
    	logger.info("In submit add game");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		// go back to the game cast view
		return "redirect:/game/cast/" + gameService.saveGame(game, storyId, user.getId(), user.getEmail()).getId();
    } 
    
    /*
     * Serves scripts.html
     * 
     * Show the script for the player
     */
    @GetMapping("/script/{gameId}")
    public String script(@PathVariable("gameId") String gameId, Model model) {
    	logger.info("In add game");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		Player player = gameService.findPlayerByUserIdAndGameId(user.getId(), gameId);

		model.addAttribute("player", player);
        
        return "scripts";
    } 
    
    /*
     * Serves clues.html
     * 
     * TBD add permission based on gameId and logged in user
     * 
     * Show the clue for the player
     */
    @GetMapping("/clue/{sceneId}")
    public String clue(@PathVariable("sceneId") Long sceneId, Model model) {
    	logger.info("User view clue");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		List<Hint> hints = gameService.findHintBySceneId(sceneId);

		model.addAttribute("hints", hints);
        
        return "clues";
    }     
    
    /*
     * Called from scripts.html
     * 
     * player executed their script
     */
    @GetMapping("/done/{gameId}")
    public String done(@PathVariable("gameId") String gameId, Model model) {
    	logger.info("User submit end game");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		Player player = gameService.done(user.getId(), gameId);
		
		model.addAttribute("player", player);
        
        return "scripts";
    }     
    
    /**
     * Serves addAnnouncement.html
     * 
     * Show announcment form
     * 
     * @param descriptor
     * @param bindingResult
     * @param model
     * @return
     */
    @GetMapping("/announcement/{gameId}/{castId}/{castName}")
    public String getAnnouncement(@PathVariable("gameId") String gameId, @PathVariable("castId") Long castId, @PathVariable("castName") String castName, Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		Game game = gameService.getGameOnly(gameId);
		
		/*
		 * check if user is part of this game
		 */
		if(!game.getEmails().contains(user.getEmail())) {
			return "redirect:/game/all";
		}
		
		Announcement yell = new Announcement();
		yell.setGame(game);
		yell.setUserId(user.getId());
		yell.setCastId(castId);
		yell.setName(castName);

		model.addAttribute("castId", castId);
		model.addAttribute("castName", castName);
		model.addAttribute("announcement", yell);
        model.addAttribute("gameId", gameId);
         
        return "addAnnouncement";
    }    

    /**
     * Post from addAnnouncement.html
     * 
     * Announcment by character of game
     * 
     * @param descriptor
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/announcement")
    public String postAnnouncement(@ModelAttribute("announcement") Announcement announcement, BindingResult bindingResult, Model model) {
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

    	// descriptorValidator.validate(descriptor, bindingResult);
    	
        if (bindingResult.hasErrors()) {
        	for(ObjectError error: bindingResult.getAllErrors()) {
        		logger.info("code " + error.getCode() + " " + error.getDefaultMessage());
        	}
            return "addAnnouncement";
        }
        
		Game game = gameService.getGameOnly(announcement.getGame().getId());
		
		/*
		 * check if user is part of this game
		 */
		if(!game.getEmails().contains(user.getEmail())) {
			return "redirect:/game/all";
		}
		
        announcement.setGame(game);
        announcement.setUserId(user.getId());       
        announcement.setEmail(user.getEmail());
        
        Announcement a = gameService.saveAnnouncement(announcement);
         
        return "redirect:/game/cast/" + announcement.getGame().getId();
    }     
    
    
    /*
     * Ajax call
     * Edit descriptor detail
     */
    /*
    @DeleteMapping(headers = IS_AJAX_HEADER)
    public void delete(@RequestHeader("id") String gameId) {
    	logger.info("In delete game " + gameId);
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return;
		}

		if(gameId == null) {
			logger.info("gameId is null");
			return;
		}
		
		gameService.deleteGame(Long.parseLong(gameId));
    }
    */

    /*
     * Serves games.html
     */    
    @RequestMapping(value = {"/all"}, method = RequestMethod.GET)
    public String all(Model model) {
    	
    	logger.info("Show all games");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		List<Game> games = gameService.findGamesByEmail(user.getEmail()); 
    		
		model.addAttribute("games", games);

		return "games";
    	
    }    

}
