package com.vclues.core.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.vclues.core.entity.Cast;

@Transactional
public interface CastRepository extends JpaRepository<Cast, Long> {

	@Modifying
	@Query("update Cast u set u.active = 0 where id = ?1")
	public void deleteCast(Long castId);
	
	public List<Cast> getAllCastByStoryId(Long storyId);
}
