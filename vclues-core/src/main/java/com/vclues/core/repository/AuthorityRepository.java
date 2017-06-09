package com.vclues.core.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vclues.core.entity.Authority;

@Transactional
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	 
	//@Cacheable(value = "byName")
	Authority findByName(String name);
	
	//@Cacheable("byId")
	Authority findById(Long id);
}
