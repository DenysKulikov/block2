package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Customer;
import com.solvd.laba.persistence.repositories.CustomerRepository;
import com.solvd.laba.service.CustomerService;

import java.sql.SQLException;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void create(Customer customer, Long paymentId) throws SQLException {
        customerRepository.create(customer, paymentId);
    }

    @Override
    public void delete(Long customerId) {
        customerRepository.delete(customerId);
    }

    @Override
    public Optional<Customer> findById(Long customerId) throws SQLException {
        return Optional.empty();
    }


    @Override
    public void addCustomerToCompany(Long customerId, Long companyId) {
        customerRepository.addCustomerToCompany(customerId, companyId);
    }

    @Override
    public void deleteCustomerFromCompany(Long customerId, Long companyId) {
        customerRepository.deleteCustomerFromCompany(customerId, companyId);
    }
}
