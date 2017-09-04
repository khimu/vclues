package com.vclues.core.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.vclues.core.entity.Script;
import com.vclues.core.entity.Story;

@Transactional
public interface StoryRepository extends JpaRepository<Story, Long> {

	@Modifying
	@Query("update Story u set u.active = 0 where id = ?1")
	public void deleteStory(Long storyId);

	@Override
	@ReadThroughSingleCache(namespace = "StoryById")
	public Story findOne(@ParameterValueKeyProvider Long storyId);
	
	@Override
	@InvalidateSingleCache(namespace = "StoryById")
	public Story save(@ParameterValueKeyProvider Story story);
}
