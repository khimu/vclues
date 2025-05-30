package com.vclues.core.service;

import java.util.List;
import java.util.Map;

import com.vclues.core.data.Announcement;
import com.vclues.core.data.Game;
import com.vclues.core.data.Player;
import com.vclues.core.entity.Hint;

public interface IGameService {
	
	public List<Hint> findHintBySceneId(Long sceneId);
	
	public Long countByEmail(String email);
	
	public List<Game> findGamesByEmail(String email);
	
	public List<Game> findGamesByEmailAndDone(String email, Boolean done);
	
	public Player findPlayerByUserIdAndGameId(Long userId, String gameId);
	
	public Player done(Long userId, String gameId);

	public Game saveGame(Game game, String storyId, Long userId, String username);
	
	public Game findOne(String id);
	
	public void saveCastForGame(String gameId, String castId, Long userId, String castName, String name);
	
	public void deleteAnnouncement(Long announcementId);
	
	public void deleteGame(Long gameId);
	
	/*
	 * used
	 */
	public Map<Integer, List<Game>> getUserGames(Long userId);
	
	/*
	 * used
	 */
	public Game getCurrentGame(Long userId);
	
	/*
	 * not used
	 */
	public List<Player> findAllCurrentGames(Long userId);
	
	/*
	 * Not used
	 */
	public Map<Integer, List<Game>> findAllUserGames(Long userId);
	
	public List<Announcement> getAllGameAnnouncements(String gameId);
	
	public void saveAnnouncement(Announcement announcement);
	
	public void saveGame(Game game);
	
	public Game getGameWithPlayers(String gameId);
	
	public Game getGameOnly(String gameId);
	
	/*
	 * used
	 */
	public Game getGameWithGameCast(String gameId);
	
	public List<String> sendInviteEmail(String email, String emails, String gameId);
	
	public void savePlayer(Player player);
	
	public List<Player> getGuesses(String gameId);
	
}
