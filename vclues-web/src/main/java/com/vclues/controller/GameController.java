package com.vclues.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vclues.core.data.Game;
import com.vclues.core.data.Player;
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

    @GetMapping("{id}")
    public String view(@PathVariable("id") String gameId, Model model) {
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
		
		Game game = gameService.getGame(gameId);

		Story story = storyService.getStory(game.getStoryId());

		model.addAttribute("story", story);
		model.addAttribute("game", game);
		//model.addAttribute("announcements", announcements);
		
		model.addAttribute("content", "gameDetail"); 
		model.addAttribute("title", "Game Detail");
        
		return "menu";
    }
    
    /*
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
		
		Game game = gameService.getGameCast(gameId);

		model.addAttribute("casts", game.getGameCast());
		
		Player player = gameService.findPlayerByUserIdAndGameId(user.getId(), gameId);

		model.addAttribute("player", player);
        
		return "cast";
    }
    
    /*
     * user has selected a murderer
     */
    @GetMapping("/murderer/{gameId}/{castId}/{castName}")
    public String murderer(@PathVariable("gameId") String gameId, @PathVariable("castId") String castId, @PathVariable("castName") String castName, Model model) {
    	logger.info("In add game");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		Game game = gameService.getGameCast(gameId);

		model.addAttribute("casts", game.getGameCast());
		
		Player player = gameService.findPlayerByUserIdAndGameId(user.getId(), gameId);
		player.setMurdererId(Long.parseLong(castId));
		
		gameService.savePlayer(player);
		
		logger.info("murderer saved with " + castId);

		model.addAttribute("player", player);
		
		logger.info("murderer id " + player.getMurdererId());
        
		return "cast";
    }  

    /*
     * user has chosen a character and now we're saving the player information
     */
    @GetMapping("/cast/{gameId}/{castId}/{castName}")
    public String addCast(@PathVariable("gameId") String gameId, @PathVariable("castId") String castId, @PathVariable("castName") String castName, Model model) {
    	logger.info("In add game");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		gameService.saveCastForGame(gameId, castId, user.getId(), castName, user.getEmail());
		
		Game game = gameService.getGameCast(gameId);

		model.addAttribute("casts", game.getGameCast());
		
		Player player = gameService.findPlayerByUserIdAndGameId(user.getId(), gameId);

		model.addAttribute("player", player);
        
		return "cast";
    }    
    
    /*
     * User has selected a story to play
     * show form for game information
     */
    @GetMapping("/select/{storyId}")
    public String showForm(@PathVariable("storyId") String storyId, Model model) {
    	logger.info("In add game");
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
     * Show the script for the player
     */
    @GetMapping("/clue/{gameId}")
    public String clue(@PathVariable("gameId") String gameId, Model model) {
    	logger.info("In add game");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}

		Player player = gameService.findPlayerByUserIdAndGameId(user.getId(), gameId);

		model.addAttribute("player", player);
        
        return "clues";
    }     
    
    /*
     * Game selection form submit
     */
    @GetMapping("/done/{gameId}")
    public String done(@PathVariable("gameId") String gameId, Model model) {
    	logger.info("In submit add game");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		Player player = gameService.done(user.getId(), gameId);
		
		model.addAttribute("player", player);
        
        return "scripts";
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

}
