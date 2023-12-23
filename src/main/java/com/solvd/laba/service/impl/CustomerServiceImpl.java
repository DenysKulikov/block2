package com.solvd.laba.service.impl;

import com.solvd.laba.domain.Customer;
import com.solvd.laba.persistence.repositories.CustomerRepository;
import com.solvd.laba.service.CustomerService;

import java.sql.SQLException;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer, Long paymentId) throws SQLException {
        return customerRepository.create(customer, paymentId);
    }

    @Override
    public void delete(Long customerId) {
        customerRepository.delete(customerId);
    }

    @Override
    public Long getCustomerId(Customer customer) throws SQLException {
        return customerRepository.getCustomerId(customer);
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
