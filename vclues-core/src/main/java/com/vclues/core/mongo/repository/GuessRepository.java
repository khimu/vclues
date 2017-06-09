package com.vclues.core.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vclues.core.data.Game;
import com.vclues.core.data.Guess;

public interface GuessRepository extends MongoRepository<Guess, String> {

	/*
	 * Check if the user in the game can choose this character
	 */
	public Guess findGuessByUserIdAndGameId(Long userId, String gameId);

	
	/*
	 * The person who initiated the game
	 */
	public List<Guess> findAllGuessByUserId(Long userId);
	
	/*
	 * Find all players in the game
	 */
	public List<Guess> findAllGuessByGame(Game game);

}
