package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Customer;

import java.sql.SQLException;

public interface CustomerRepository {
    Customer create(Customer customer, Long paymentId) throws SQLException;
}
