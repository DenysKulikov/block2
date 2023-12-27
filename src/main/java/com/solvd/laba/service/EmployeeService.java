package com.solvd.laba.service;

import com.solvd.laba.domain.Employee;
import com.solvd.laba.domain.Position;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface EmployeeService {
    void create(@Param("employee") Employee employee, @Param("companyId") Long companyId, @Param("salaryId") Long salaryId, @Param("positionName") String positionName) throws SQLException;
    void addEmployeeToBuilding(@Param("employeeId") Long employeeId, @Param("buildingId") Long buildingId);
    void deleteEmployeeFromBuilding(Long employeeId, Long buildingId);
    void delete(Long employeeId);
}
