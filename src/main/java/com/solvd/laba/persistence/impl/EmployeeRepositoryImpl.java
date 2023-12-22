package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.Employee;
import com.solvd.laba.domain.enums.Position;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.EmployeeRepository;

import java.sql.*;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    @Override
    public Employee create(Employee employee, Long companyId, Long salaryId, Position position) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO employees(first_name, last_name, position, company_id, salary_id) VALUES (?, ?, ?, ?, ?)";
        connection.setAutoCommit(false);

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, position.name());
            preparedStatement.setLong(4, companyId);
            preparedStatement.setLong(5, salaryId);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                employee.setId(resultSet.getLong(1));
            }

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
            CONNECTION_POOL.releaseConnection(connection);
        }
        return employee;
    }


}
