package com.vclues.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vclues.core.entity.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

    public Balance findBalanceByUserId(Long userId) throws UsernameNotFoundException;

}
