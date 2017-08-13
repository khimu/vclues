package com.vclues.core.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.vclues.core.entity.Cast;
import com.vclues.core.entity.Hint;

@Transactional
public interface HintRepository extends JpaRepository<Hint, Long> {

	@Modifying
	@Query("update Hint u set u.active = 0 where id = ?1")
	public void deleteHint(Long hintId);
	
	public Hint getAllHintBySceneId(Long sceneId);
	
	public List<Hint> getAllHintBySceneIdAndPositionLessThan(Long sceneId, Integer position);

	public Hint findHintBySceneId(Long sceneId);
}
