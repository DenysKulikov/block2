package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Employee;
import com.solvd.laba.domain.enums.Position;

import java.sql.SQLException;

public interface EmployeeRepository {
    Employee create(Employee employee, Long companyId, Long salaryId, Position position) throws SQLException;
    void delete(Long employeeId);
}
