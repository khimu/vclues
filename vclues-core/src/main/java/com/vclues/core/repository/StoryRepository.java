package com.vclues.core.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.vclues.core.entity.Story;
import com.vclues.core.entity.User;

@Transactional
public interface StoryRepository extends JpaRepository<Story, Long> {

	@Modifying
	@Query("update Story u set u.active = 0 where id = ?1")
	public void deleteStory(Long storyId);

	@Override
	@ReadThroughSingleCache(namespace = "StoryById")
	public Story findOne(@ParameterValueKeyProvider Long storyId);
	
	@Override
	@CacheEvict(value = "StoryById", key = "#p0.story.id")
	public Story save(@ParameterValueKeyProvider Story story);
	
	
	public List<Story> findAllStoryByUser(User user);
	
	public List<Story> findAllActive(boolean active);
}
