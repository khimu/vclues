package com.vclues.core.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vclues.core.data.GameCast;

public interface GameCastRepository extends MongoRepository<GameCast, String> {

}
