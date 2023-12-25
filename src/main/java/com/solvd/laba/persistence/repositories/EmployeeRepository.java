package com.solvd.laba.persistence.repositories;

import com.solvd.laba.domain.Employee;
import com.solvd.laba.domain.enums.Position;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface EmployeeRepository {
    void create(@Param("employee") Employee employee, @Param("companyId") Long companyId, @Param("salaryId") Long salaryId, @Param("position") Position position) throws SQLException;
    void delete(Long employeeId);
    void update(Employee employee, Long employeeId) throws SQLException;
    void addEmployeeToBuilding(@Param("employeeId") Long employeeId, @Param("buildingId") Long buildingId);
    void deleteEmployeeFromBuilding(@Param("employeeId") Long employeeId, @Param("buildingId") Long buildingId);
}
