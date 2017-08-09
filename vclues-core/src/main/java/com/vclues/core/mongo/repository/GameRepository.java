package com.vclues.core.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vclues.core.data.Game;
import com.vclues.core.data.Player;

public interface GameRepository extends MongoRepository<Game, String> {

    public List<Game> findGamesByUserIdAndDone(Long userId, Boolean done);

    public Game findGameById(Long gameId);
    
    public List<Game> findGamesByPlayers(List<Player> players, boolean done);
}
