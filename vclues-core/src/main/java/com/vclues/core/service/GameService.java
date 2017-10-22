package com.vclues.core.service;

import it.ozimov.springboot.templating.mail.model.Email;
import it.ozimov.springboot.templating.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.templating.mail.service.EmailService;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.internet.InternetAddress;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.vclues.core.app.Constant;
import com.vclues.core.data.Announcement;
import com.vclues.core.data.Game;
import com.vclues.core.data.GameCast;
import com.vclues.core.data.Player;
import com.vclues.core.entity.Cast;
import com.vclues.core.entity.Hint;
import com.vclues.core.entity.Scene;
import com.vclues.core.entity.Script;
import com.vclues.core.entity.Story;
import com.vclues.core.entity.User;
import com.vclues.core.mongo.repository.AnnouncementRepository;
import com.vclues.core.mongo.repository.GameCastRepository;
import com.vclues.core.mongo.repository.GameRepository;
import com.vclues.core.mongo.repository.PlayerRepository;
import com.vclues.core.repository.CastRepository;
import com.vclues.core.repository.HintRepository;
import com.vclues.core.repository.SceneRepository;
import com.vclues.core.repository.ScriptRepository;
import com.vclues.core.repository.StoryRepository;

@Service
public class GameService implements IGameService {
	
	private final Logger logger = LoggerFactory.getLogger(GameService.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private SceneRepository sceneRepository;
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private CastRepository castRepository;
	
	@Autowired
	private GameCastRepository gameCastRepository;

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private HintRepository hintRepository;
	
	@Autowired
	private ScriptRepository scriptRepository;
	
	@Autowired
	private AnnouncementRepository announcementRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
    @Autowired
    public EmailService emailService;
    
    @Value("${spring.mail.username}")
    private String myEmail;
    
    @Value("${server.base.url}")
    private String baseUrl;
    
    public List<Hint> findHintBySceneId(Long sceneId) {
    	return hintRepository.findHintBySceneId(sceneId);
    }
    
	@Override
	public List<Player> findAllCurrentGames(Long userId) {
		// find all games that the user is involved in
		List<Player> players = playerRepository.findAllPlayersByUserId(userId);
		List<Game> currentGames = new ArrayList<Game>();
	
		for(Player p : players) {
			
			if(!p.isDone()) {
				Scene scene = sceneRepository.findOne(p.getGame().getSceneId());
				List<Announcement> announcements = announcementRepository.findAllByGame(p.getGame());
				
				if(scene != null) {
					List<Hint> hints = hintRepository.getAllHintBySceneIdAndPositionLessThan(p.getGame().getSceneId(), scene.getPosition());
					List<Script> scripts = scriptRepository.getAllScriptsBySceneIdAndPositionLessThan(p.getGame().getSceneId(), scene.getPosition());					
					p.setHints(hints);
					p.setScripts(scripts);
				}
				
				p.setAnnouncements(announcements);
			}// end p is done
		}//end p for loop
		
    	return players;
	}
	
	@Override
	public Map<Integer, List<Game>> findAllUserGames(Long userId) {
		List<Player> players = playerRepository.findAllPlayersByUserId(userId);
		List<Game> currentGames = new ArrayList<Game>();
		List<Game> pastGames = new ArrayList<Game>();
		
		for(Player p : players) {
			List<Player> ps = playerRepository.findAllPlayersByGame(p.getGame());
			if(p.getGame().getDone().booleanValue()) {
				Game game = p.getGame();
				game.setPlayers(ps);
				pastGames.add(game);
			}
			else {
				Game game = p.getGame();
				game.setPlayers(ps);
			}
		}
		
    	Map<Integer, List<Game>> games = new HashMap<Integer, List<Game>>();
    	games.put(Constant.CURRENT, currentGames);
    	games.put(Constant.PAST, pastGames);
        return games; 
	}
	
	@Override
	public Game saveGame(Game game, String storyId, Long userId, String username) {
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		Calendar calendar = Calendar.getInstance(timeZone);
		// mm/dd/yyyy hh:mm:ss
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.DATE_FORMAT, Locale.US);
		simpleDateFormat.setTimeZone(timeZone);	

		Story story = storyRepository.findOne(Long.parseLong(storyId));
		
		/*
		 * Get scene 1
		 */
		Scene scene = sceneRepository.getNextSceneByStoryIdAndPosition(Long.parseLong(storyId), 1);
		
		Date today = calendar.getTime();

		Player player = new Player();
		player.setUserId(userId);
		player.setName(username);

		/*
		 * start at scene 1
		 */
		game.setSceneId(scene.getId());
		game.setName(story.getTitle());
		game.setCount(story.getSize());

		game.setStarted(today);
		game.setLastShown(today);
		game.setStoryId(Long.parseLong(storyId));
		game.setUserId(userId);
		
		Game savedGame = gameRepository.save(game);

		List<Cast> casts = castRepository.getAllCastByStoryId(Long.parseLong(storyId));
		
		for(Cast cast : casts) {
			GameCast gameCast = new GameCast();
			gameCast.setCastId(cast.getId());
			gameCast.setGame(savedGame);
			gameCastRepository.save(gameCast);
			savedGame.getGameCast().add(gameCast);
		}
		
		if(game.getInvites() != null) {
			savedGame.setEmails(sendInviteEmail(username, game.getInvites().toLowerCase(), savedGame.getId()));
		}
		
		// Add the current host player
		savedGame.getEmails().add(username);
		savedGame.setCount(savedGame.getEmails().size());

		player.setGame(savedGame);
		playerRepository.save(player);
		
		savedGame.getPlayers().add(player);
		
		gameRepository.save(savedGame);

		return savedGame;
	}
	
	@Override
	public Game findOne(String id) {
		return gameRepository.findOne(id);
	}
	
	@Override
	public void saveCastForGame(String gameId, String castId, Long userId, String castName, String name) {
		Player player = playerRepository.findPlayerByUserIdAndGameId(userId, gameId);
		if(player == null) {
			player = new Player();
		}
		
		Player taken = playerRepository.findPlayerByCastIdAndGameId(Long.parseLong(castId), gameId);
		
		if(taken != null && !taken.getUserId().equals(userId)) {
			logger.info("No Update due to character already taken by " + taken.getUserId());
			return;
		}
		
		if(player != null && player.getCastId() != null && !player.getCastId().equals(Long.parseLong(castId))) {
			logger.info("User already choose a different character " + player.getUserId() + ".  Allowing user to choose a new character");
		}
		
		Game game = gameRepository.findOne(gameId);
		
		if(game.getStarted() == null) {
			game.setStarted(new Date());
		}
		
		Script script = scriptRepository.findScriptBySceneIdAndCastId(game.getSceneId(), Long.parseLong(castId));
		//Hint hint = hintRepository.getAllHintBySceneId(game.getSceneId());
		
		player.setCastId(Long.parseLong(castId));
		player.setName(name);
		player.setCastName(castName);
		player.setUserId(userId);
		player.setScript(script);
		player.setScriptId(script.getId());
		//player.setHintId(hint.getId());

		/*
		 * remove self from previous character selection
		 */
		for(GameCast cast : game.getGameCast()) {
			if(cast.getUsername() != null && cast.getUsername().equals(player.getName())) {
				cast.setUsername(null);
				gameCastRepository.save(cast);
				break;
			}
		}
		
		/*
		 * assign to new character selection
		 */
		for(GameCast cast : game.getGameCast()) {
			if(cast.getCastId().toString().equals(castId)) {
				cast.setUsername(player.getName());
				gameCastRepository.save(cast);
				break;
			}
		}
		
		player.setGame(game);
		
		playerRepository.save(player);
		gameRepository.save(game);
	}
	
	@Override
	public void deleteAnnouncement(Long announcementId) {
	}

	@Override
	public void deleteGame(Long gameId) {
	}

	@Override
	public Announcement saveAnnouncement(Announcement announcement) {
		return announcementRepository.save(announcement);
	}

	@Override
	public void saveGame(Game game) {
		gameRepository.save(game);
	}
	
	@Override
	public Game getGameOnly(String gameId) {
		Game game = gameRepository.findOne(gameId);
		return game;
	}	

	/*
	 * Called from GameController done(String, Model)
	 * 
	 * (non-Javadoc)
	 * @see com.vclues.core.service.IGameService#getGame(java.lang.String)
	 */
	@Override
	public Game getGameWithPlayers(String gameId) {
		Game game = gameRepository.findOne(gameId);
		List<Player> players = playerRepository.findAllPlayersByGame(game);
		game.setPlayers(players);
		return game;
	}
	
	/*
	 * Not used
	 * 
	 * (non-Javadoc)
	 * @see com.vclues.core.service.IGameService#getUserGames(java.lang.Long)
	 */
	@Override
    public Map<Integer, List<Game>> getUserGames(Long userId) {
    	Map<Integer, List<Game>> games = new HashMap<Integer, List<Game>>();
    	games.put(1, gameRepository.findGamesByUserIdAndDone(userId, true));
    	games.put(2, gameRepository.findGamesByUserIdAndDone(userId, false));
        return games;        
    }
    
	@Override
    public List<Announcement> getAllGameAnnouncements(String gameId) {
		Game game = gameRepository.findOne(gameId);
		List<Cast> casts = castRepository.getAllCastByStoryId(game.getStoryId());

		Map<Long, Cast> map = new HashMap<Long, Cast>();
		for(Cast cast : casts) {
			map.put(cast.getId(), cast);
		}
		
		List<Announcement> announcements = announcementRepository.findAllByGame(game);
		
		for(Announcement a : announcements) {
			a.setCast(map.get(a.getCastId()));
		}
		
		return announcements;
    }
	
	private final static ExecutorService executor = Executors.newCachedThreadPool();
	
	@Override
	public List<String> sendInviteEmail(String email, String emails, String gameId) {
		List<String> results = new ArrayList<String>();
		String[] tmp = emails.split(",");
		
		Game game = gameRepository.findOne(gameId);
		
		for(String em : tmp) {
			final String invitee = em.trim();
			results.add(invitee);
			
			String password = RandomStringUtils.randomAlphabetic(5);
			
			User user = userService.findByEmail(invitee);
			boolean newuser = false;
			if(user == null) {
				user = new User();
				user.setEmail(invitee);
				user.setPassword(password);
			    user = userService.registerNewUser(user);
			    newuser = true;
			}

			Player player = new Player();
			player.setUserId(user.getId());
			player.setName(invitee);
			player.setGame(game);
			
			playerRepository.save(player);
			game.getPlayers().add(player);
			

			final boolean finalnewuser = newuser;
			final User finalUser = user;
			executor.submit(() -> { 
				try {
					// This needs to be asynchronous
			        final Email invite = DefaultEmail.builder()
			                .from(new InternetAddress(myEmail, "VClues"))
			                .to(Lists.newArrayList(new InternetAddress(invitee)))
			                .subject("Vega Clues Invite")
			                .body("")//Empty body
			                .encoding(Charset.forName("UTF-8").name()).build();
			            
			        final Map<String, Object> modelObject = new HashMap<>();

			        modelObject.put("email", email);
			        modelObject.put("url", baseUrl);

			        if(finalnewuser) {
			        	modelObject.put("password", password);
			        }
			        else {
			        	modelObject.put("password", "(Your Current Login Password and Username " + finalUser.getEmail() + ")");
			        }
			        emailService.send(invite, "emails/invite.ftl", modelObject);  
				}catch(Exception e) {
					logger.error("Unable to email invite user " + finalUser.getEmail());
					e.printStackTrace();
				}		
			});

		}
		
		gameRepository.save(game);
		
		return results;
	}

	@Override
	public Game getGameWithGameCast(String gameId) {
		Game game = gameRepository.findOne(gameId);
		List<Cast> casts = castRepository.getAllCastByStoryId(game.getStoryId());
		
		Map<Long, Cast> ids = new HashMap<Long, Cast>();
		
		for(Cast cast : casts) {
			ids.put(cast.getId(), cast);
		}
		
		for(GameCast cg : game.getGameCast()) {
			Cast cast = ids.get(cg.getCastId());
			cg.setCast(cast);
		}
		
		return game;
	}

	/* Not used */
	@Override
	public Game getCurrentGame(Long userId) {
		List<Game> games = gameRepository.findGamesByUserIdAndDone(userId, false);
		if(games != null && games.size() > 0) {
			List<Player> players = playerRepository.findAllPlayersByGame(games.get(0));
			games.get(0).setPlayers(players);
			return games.get(0);
		}

		return null;
	}
	
	/*
	 * Not used
	 * 
	 * (non-Javadoc)
	 * @see com.vclues.core.service.IGameService#findGamesByEmailAndDone(java.lang.String, java.lang.Boolean)
	 */
	@Override
	public List<Game> findGamesByEmailAndDone(String email, Boolean done) {
		List<Game> games = gameRepository.findGamesByEmailAndDone(email, done);
		
		for(Game g : games) {
			List<Player> players = playerRepository.findAllPlayersByGame(g);
			
			for(Player p : players) {
				if(p.getMurdererId() != null) {
					Cast cast = castRepository.findOne(p.getMurdererId());
					p.setGuess(cast);
				}
				if(p.getScriptId() != null) {
					p.setScript(scriptRepository.findOne(p.getScriptId()));
				}
				
				if(p.getHintId() != null) {
					p.setHint(hintRepository.findOne(p.getHintId()));
				}				
			}
		}
		
		return games;
	}
	
	public Long countByEmail(String email) {
		return gameRepository.countByEmails(email);
	}
	
	@Override
	public List<Game> findGamesByEmail(String email) {
		Sort sort = new Sort(Direction.DESC, "order");
		List<Game> games = gameRepository.findGamesByEmail(email, sort);
		
		for(Game g : games) {
			List<Player> players = playerRepository.findAllPlayersByGame(g);
			
			for(Player p : players) {
				if(p.getMurdererId() != null) {
					Cast cast = castRepository.findOne(p.getMurdererId());
					p.setGuess(cast);
				}
				if(p.getScriptId() != null) {
					p.setScript(scriptRepository.findOne(p.getScriptId()));
				}
				
				if(p.getHintId() != null) {
					p.setHint(hintRepository.findOne(p.getHintId()));
				}				
			}
		}
		
		return games;
	}
	
	public List<Player> getGuesses(String gameId) {
		Game game = gameRepository.findOne(gameId);
		List<Player> players = playerRepository.findAllPlayersByGame(game);
		
		for(Player p : players) {
			if(p.getMurdererId() != null) {
				Cast cast = castRepository.findOne(p.getMurdererId());
				p.setGuess(cast);
			}
		}
		
		return players;
	}
	
	public Player findPlayerByUserIdAndGameId(Long userId, String gameId) {
		Player player = playerRepository.findPlayerByUserIdAndGameId(userId, gameId);
		
		if(player == null) {
			logger.error("player is null for " + userId + " gameId " + gameId);
		}
		
		if(player.getScriptId() != null) {
			player.setScript(scriptRepository.findOne(player.getScriptId()));
		}
		
		if(player.getHintId() != null) {
			player.setHint(hintRepository.findOne(player.getHintId()));
		}
		
		return player;
	}
	
	public Player done(Long userId, String gameId) {
		Game game = gameRepository.findOne(gameId);

		Long count = playerRepository.countByGameIdAndDone(gameId, true);
		
		/*
		 * all players are done with scene
		 */
		if(game.getGameCast().size() == count.intValue()) {
			Scene currentScene = sceneRepository.findOne(game.getSceneId());
			Scene nextScene = sceneRepository.getNextSceneByStoryIdAndPosition(game.getStoryId(), currentScene.getPosition() + 1);
			
			/*
			 * set next scene (script/clues) for all players
			 */
			if(nextScene != null) {
				//List<Hint> hint = hintRepository.findHintBySceneId(nextScene.getId());
				List<Script> scripts = scriptRepository.getAllScriptsBySceneId(nextScene.getId());
				
				Map<Long, Script> castToScript = new HashMap<Long, Script>();
				
				for(Script s : scripts) {
					castToScript.put(s.getCast().getId(), s);
				}
				
				List<Player> players = playerRepository.findAllPlayersByGame(game);
				
				for(Player p : players) {
					//p.setHintId(hint.getId());
					p.setScriptId(castToScript.get(p.getCastId()).getId());
					p.setDone(false);
					playerRepository.save(p);
				}
				
				game.setSceneId(nextScene.getId());
				
			}
			else {
				game.setDone(true);
			}
			
			gameRepository.save(game);
			
			return this.findPlayerByUserIdAndGameId(userId, gameId);
		}

		/*
		 * not all players are done
		 */
		Player player = playerRepository.findPlayerByUserIdAndGameId(userId, gameId);
		player.setDone(true);
		playerRepository.save(player);
		
		return this.findPlayerByUserIdAndGameId(userId, gameId);
	}
	
	public void savePlayer(Player player) {
		logger.info("murderer id is " + player.getMurdererId());
		playerRepository.save(player);
	}
}
