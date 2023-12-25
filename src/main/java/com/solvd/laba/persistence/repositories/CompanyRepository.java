package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Company;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CompanyRepository {
    Company create(Company company) throws SQLException;
    void delete(Long companyId);
    void update(@Param("company") Company company, @Param("companyId") Long companyId);
    List<Company> findAll();
    Optional<Company> findById(Long companyId) throws SQLException;
}
