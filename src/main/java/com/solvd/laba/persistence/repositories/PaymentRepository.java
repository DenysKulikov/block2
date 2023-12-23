package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Payment;

import java.sql.SQLException;

public interface PaymentRepository {
    Payment create(Payment payment);
    void delete(Long salaryId);
    Long getPaymentId(Payment payment) throws SQLException;
}
