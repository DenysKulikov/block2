package com.solvd.laba.service;

import com.solvd.laba.domain.Employee;
import com.solvd.laba.domain.Position;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface EmployeeService {
    void create(Employee employee, Long companyId, Long salaryId, String positionName) throws SQLException;
    void addEmployeeToBuilding(Long employeeId, Long buildingId);
    void deleteEmployeeFromBuilding(Long employeeId, Long buildingId);
    void delete(Long employeeId);
}
