package com.vclues.core.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vclues.core.entity.Balance;

public interface IBalanceService {
	public Balance findBalanceByUserId(Long userId) throws UsernameNotFoundException;
	
	public Balance save(Balance balance);
}
