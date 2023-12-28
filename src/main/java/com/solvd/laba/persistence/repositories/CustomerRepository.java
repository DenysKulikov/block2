package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Customer;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface CustomerRepository {
    void create(@Param("customer") Customer customer, @Param("paymentId") Long paymentId) throws SQLException;
    void delete(Long customerId);
    Long getCustomerId(Customer customer) throws SQLException;
    void addCustomerToCompany(@Param("customerId") Long customerId, @Param("companyId") Long companyId);
    void deleteCustomerFromCompany(@Param("customerId")Long customerId, @Param("companyId") Long companyId);
}
