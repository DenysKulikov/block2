package com.solvd.laba.persistence.mybatisImpl;

import com.solvd.laba.domain.Company;
import com.solvd.laba.persistence.Config;
import com.solvd.laba.persistence.repositories.CompanyRepository;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CompanyRepositoryMybatisIpl implements CompanyRepository {
    @Override
    public void create(Company company) throws SQLException {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = sqlSession.getMapper(CompanyRepository.class);
            companyRepository.create(company);
        }
    }

    @Override
    public void delete(Long companyId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = sqlSession.getMapper(CompanyRepository.class);
            companyRepository.delete(companyId);
        }
    }

    @Override
    public void update(Company company, Long companyId) {

    }

    @Override
    public List<Company> findAll() {
        return null;
    }

    @Override
    public Optional<Company> findById(Long companyId) throws SQLException {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = sqlSession.getMapper(CompanyRepository.class);
            return companyRepository.findById(companyId);
        }
    }
}
