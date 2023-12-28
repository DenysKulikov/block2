package com.solvd.laba.persistence.mybatisImpl;

import com.solvd.laba.domain.Salary;
import com.solvd.laba.persistence.Config;
import com.solvd.laba.persistence.repositories.SalaryRepository;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.Optional;

public class SalaryRepositoryMybatisImpl implements SalaryRepository {
    @Override
    public void create(Salary salary) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            SalaryRepository salaryRepository = sqlSession.getMapper(SalaryRepository.class);
            salaryRepository.create(salary);
        }
    }

    @Override
    public void delete(Long salaryId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            SalaryRepository salaryRepository = sqlSession.getMapper(SalaryRepository.class);
            salaryRepository.delete(salaryId);
        }
    }

    @Override
    public Optional<Salary> findById(Long salaryId) throws SQLException {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            SalaryRepository salaryRepository = sqlSession.getMapper(SalaryRepository.class);
            return salaryRepository.findById(salaryId);
        }
    }
}
