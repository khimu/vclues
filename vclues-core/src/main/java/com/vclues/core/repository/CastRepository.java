package com.vclues.core.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.vclues.core.entity.Cast;
import com.vclues.core.entity.Story;

@Transactional
public interface CastRepository extends JpaRepository<Cast, Long> {

	@Modifying
	@Query("update Cast u set u.active = 0 where id = ?1")
	@CacheEvict(value = "getAllCastByStoryId")
	public void deleteCast(Long castId);
	
	//@Cacheable(value="getAllCastByStoryId")
	@ReadThroughSingleCache(namespace = "CastByStoryId")
	public List<Cast> getAllCastByStoryId(@ParameterValueKeyProvider Long storyId);
	
	@Override
	@InvalidateSingleCache(namespace = "CastByStoryId")
	public Cast save(@ParameterValueKeyProvider Cast cast);
	
}
