package com.vclues.core.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.vclues.core.data.Game;
import com.vclues.core.data.Player;

public interface PlayerRepository extends MongoRepository<Player, String> {

	/*
	 * Check if the user in the game can choose this character
	 */
	public Player findPlayerByUserIdAndGameId(Long userId, String gameId);
	
	/*
	 * Another member of the game already choose the character 
	 */
	public Player findPlayerByCastIdAndGameId(Long castId, String gameId);
	
	public Long countByGameIdAndDone(String gameId, boolean done);
	
	/*
	 * The person who initiated the game
	 */
	public List<Player> findAllPlayersByUserId(Long userId);
	
	/*
	 * Find all players in the game
	 */
	public List<Player> findAllPlayersByGame(Game game);

    @Override
    @InvalidateSingleCache(namespace = "PlayerById")
    public Player save(@ParameterValueKeyProvider Player player);

	@Override
	@ReadThroughSingleCache(namespace = "PlayerById")
	public Player findOne(@ParameterValueKeyProvider String playerId);
}
