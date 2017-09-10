package com.vclues.core.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.vclues.core.data.GameCast;

public interface GameCastRepository extends MongoRepository<GameCast, String> {

}
