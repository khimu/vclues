package com.vclues.core.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.vclues.core.entity.Scene;

@Transactional
public interface SceneRepository extends JpaRepository<Scene, Long> {

	@Modifying
	@Query("update Scene u set u.active = 0 where id = ?1")
	public void deleteScene(Long sceneId);

	@Cacheable(value = "getAllSceneByStoryId")
	public List<Scene> getAllSceneByStoryId(Long storyId);
	
	@Query("select count(e) from Scene e where e.story.id = ?1")
	public Integer countByStoryId(Long storyId);

	@Cacheable(value = "getNextSceneByStoryIdAndPosition")
	public Scene getNextSceneByStoryIdAndPosition(Long storyId, Integer position);
	
	
}
