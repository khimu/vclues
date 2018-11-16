package com.vclues.core.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.vclues.core.entity.Scene;
import com.vclues.core.entity.Story;

@Transactional
public interface SceneRepository extends JpaRepository<Scene, Long> {

	@Modifying
	@Query("update Scene u set u.active = 0 where id = ?1")
	public void deleteScene(Long sceneId);
	
	public void deleteByStoryId(Long storyId);
	
	public List<Scene> getAllSceneByStoryId(Long storyId);
	
	//@ReadThroughSingleCache(namespace = "countByStoryId")
	@Query("select count(e) from Scene e where e.story.id = ?1")
	public Integer countByStoryId(Long storyId);
	
	//@ReadThroughSingleCache(namespace = "getNextSceneByStoryIdAndPosition")
	public Scene getNextSceneByStoryIdAndPosition(Long storyId, Integer position);
	
	@Override
	//@InvalidateSingleCache(namespace = "getNextSceneByStoryIdAndPosition")
	public Scene save(Scene scene);
}
