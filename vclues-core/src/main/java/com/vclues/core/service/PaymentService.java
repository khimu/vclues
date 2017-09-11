package com.vclues.core.service;

import org.springframework.stereotype.Service;

import com.vclues.core.entity.Payment;
import com.vclues.core.repository.PaymentRepository;

@Service("paymentService")
public class PaymentService implements IPaymentService {
	
	private PaymentRepository paymentRepository;

	public Payment save(Payment payment) {
		return paymentRepository.save(payment);
	}
}
