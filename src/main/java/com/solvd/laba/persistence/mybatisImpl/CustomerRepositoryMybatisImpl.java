package com.solvd.laba.persistence.mybatisImpl;

import com.solvd.laba.domain.Customer;
import com.solvd.laba.persistence.Config;
import com.solvd.laba.persistence.repositories.CustomerRepository;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class CustomerRepositoryMybatisImpl implements CustomerRepository {
    @Override
    public void create(Customer customer, Long paymentId) throws SQLException {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            customerRepository.create(customer, paymentId);
        }
    }

    @Override
    public void delete(Long customerId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            customerRepository.delete(customerId);
        }
    }

    @Override
    public Long getCustomerId(Customer customer) throws SQLException {
        return null;
    }

    @Override
    public void addCustomerToCompany(Long customerId, Long companyId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            customerRepository.addCustomerToCompany(customerId, companyId);
        }
    }

    @Override
    public void deleteCustomerFromCompany(Long customerId, Long companyId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            customerRepository.deleteCustomerFromCompany(customerId, companyId);
        }
    }
}
