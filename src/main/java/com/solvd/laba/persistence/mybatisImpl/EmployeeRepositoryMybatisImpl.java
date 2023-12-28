package com.solvd.laba.persistence.mybatisImpl;

import com.solvd.laba.domain.Employee;
import com.solvd.laba.persistence.Config;
import com.solvd.laba.persistence.repositories.EmployeeRepository;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class EmployeeRepositoryMybatisImpl implements EmployeeRepository {
    @Override
    public void create(Employee employee, Long companyId, Long salaryId, String positionName) throws SQLException {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.create(employee, companyId, salaryId, positionName);
        }
    }

    @Override
    public void delete(Long employeeId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.delete(employeeId);
        }
    }

    @Override
    public void update(Employee employee, Long employeeId) throws SQLException {

    }

    @Override
    public void addEmployeeToBuilding(Long employeeId, Long buildingId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.addEmployeeToBuilding(employeeId, buildingId);
        }
    }

    @Override
    public void deleteEmployeeFromBuilding(Long employeeId, Long buildingId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.deleteEmployeeFromBuilding(employeeId, buildingId);
        }
    }
}
