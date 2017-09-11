package com.vclues.core.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vclues.core.entity.Balance;
import com.vclues.core.entity.Payment;
import com.vclues.core.repository.BalanceRepository;
import com.vclues.core.repository.PaymentRepository;

@Service("balanceService")
public class BalanceService implements IBalanceService {

	private BalanceRepository balanceRepository;

	@Override
	public Balance findBalanceByUserId(Long userId) throws UsernameNotFoundException {
		return balanceRepository.findBalanceByUserId(userId);
	}

	public Balance save(Balance balance) {
		return balanceRepository.save(balance);
	}

}
