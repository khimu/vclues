package com.vclues.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vclues.core.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
