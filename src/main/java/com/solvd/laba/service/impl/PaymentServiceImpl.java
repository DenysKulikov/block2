package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Payment;
import com.solvd.laba.persistence.repositories.PaymentRepository;
import com.solvd.laba.service.PaymentService;

import java.sql.SQLException;

public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void create(Payment payment) {
        paymentRepository.create(payment);
    }

    @Override
    public void delete(Long paymentId) {
        paymentRepository.delete(paymentId);
    }

    @Override
    public Long getPaymentId(Payment payment) throws SQLException {
        return paymentRepository.getPaymentId(payment);
    }
}
