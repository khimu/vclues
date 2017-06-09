package com.vclues.core.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vclues.core.data.Announcement;
import com.vclues.core.data.Game;

public interface AnnouncementRepository extends MongoRepository<Announcement, String> {
    

	public List<Announcement> findAllByGame(Game game);
}
