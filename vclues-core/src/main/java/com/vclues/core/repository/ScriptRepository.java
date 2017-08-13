package com.vclues.core.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.vclues.core.entity.Hint;
import com.vclues.core.entity.Script;

@Transactional
public interface ScriptRepository extends JpaRepository<Script, Long> {

	@Modifying
	@Query("update Script u set u.active = 0 where id = ?1")
	public void deleteScript(Long scriptId);
	
	public List<Script> getAllScriptsBySceneId(Long sceneId);
	
	public List<Script> getAllScriptsBySceneIdAndPositionLessThan(Long sceneId, Integer position);
	
	public Script findScriptBySceneIdAndCastId(Long sceneId, Long castId);
}
