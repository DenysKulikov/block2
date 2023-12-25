package com.solvd.laba.service;

import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.Customer;

import java.sql.SQLException;
import java.util.Optional;

public interface CustomerService {
    void create(Customer customer, Long paymentId) throws SQLException;
    void delete(Long customerId);
    Optional<Customer> findById(Long customerId) throws SQLException;
    void addCustomerToCompany(Long customerId, Long companyId);
    void deleteCustomerFromCompany(Long customerId, Long companyId);
}
