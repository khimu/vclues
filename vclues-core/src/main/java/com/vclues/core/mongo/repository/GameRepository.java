package com.vclues.core.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughMultiCache;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.vclues.core.data.Game;
import com.vclues.core.data.Player;

public interface GameRepository extends MongoRepository<Game, String> {

    public List<Game> findGamesByUserIdAndDone(Long userId, Boolean done);
    
    //@Query("select c from Game c where c.emails = :email and done = false")
    //public List<Game> findGamesByDoneAndEmailsIn(@Param("email") String email, Boolean done);
    
    
    @Query(value = "{ 'emails' : { $all: [?0] }, 'done' : false }")
    public List<Game> findGamesByEmailAndDone(String email, Boolean done);
        
    @ReadThroughSingleCache(namespace = "GamesByEmail")
    @Query(value = "{ 'emails' : { $all: [?0] }}")
    public List<Game> findGamesByEmail(@ParameterValueKeyProvider String email);
    
    @ReadThroughSingleCache(namespace = "CountByEmail")
    public Long countByEmails(@ParameterValueKeyProvider String email);
    
    @ReadThroughSingleCache(namespace = "Games")
    public Game findGameById(Long gameId);
    
    @ReadThroughMultiCache(namespace = "GamesByPlayers")
    public List<Game> findGamesByPlayers(@ParameterValueKeyProvider List<Player> players, boolean done);
    
    @Override
    @InvalidateSingleCache(namespace = "GamesByPlayers")
    public Game save(@ParameterValueKeyProvider Game game);
    
}
