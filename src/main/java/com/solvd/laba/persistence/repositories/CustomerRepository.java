package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Customer;

import java.sql.SQLException;

public interface CustomerRepository {
    Customer create(Customer customer, Long paymentId) throws SQLException;
    void delete(Long customerId);
    Long getCustomerId(Customer customer) throws SQLException;
    void addCustomerToCompany(Long customerId, Long companyId);
    void deleteCustomerFromCompany(Long customerId, Long companyId);
}
