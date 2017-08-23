package com.vclues.core.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.vclues.core.entity.Story;

@Transactional
public interface StoryRepository extends JpaRepository<Story, Long> {

	@Modifying
	@Query("update Story u set u.active = 0 where id = ?1")
	@CacheEvict("storiesFindAll")
	public void deleteStory(Long storyId);
	
	@Override
	@CacheEvict("storiesFindAll")
	public Story save(Story entity);

	@Override
	@Cacheable(value = "storiesFindAll")
	public List<Story> findAll();
}
