package com.solvd.laba.service;

import com.solvd.laba.domain.Payment;

import java.sql.SQLException;

public interface PaymentService {
    Payment create(Payment payment);
    void delete(Long paymentId);
    Long getPaymentId(Payment payment) throws SQLException;
}
