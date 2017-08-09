package com.vclues.core.service;

import java.util.List;
import java.util.Map;

import com.vclues.core.data.Announcement;
import com.vclues.core.data.Game;
import com.vclues.core.data.Player;

public interface IGameService {

	public Game saveGame(Game game, String storyId, Long userId, String username);
	
	public Game findOne(String id);
	
	public void saveCastForGame(String gameId, String castId, Long userId, String castName, String name);
	
	public void deleteAnnouncement(Long announcementId);
	
	public void deleteGame(Long gameId);
	
	/*
	 * used
	 */
	public Map<Integer, List<Game>> getUserGames(Long userId);
	
	public Game getCurrentGame(Long userId);
	
	public List<Player> findAllCurrentGames(Long userId);
	
	/*
	 * Not used
	 */
	public Map<Integer, List<Game>> findAllUserGames(Long userId);
	
	public List<Announcement> getAllGameAnnouncements(Game game);
	
	public void saveAnnouncement(Announcement announcement);
	
	public void saveGame(Game game);
	
	public Game getGame(String gameId);
	
	public Game getGameCast(String gameId);
	
	public List<String> sendInviteEmail(String email, String emails, String gameId);
}
