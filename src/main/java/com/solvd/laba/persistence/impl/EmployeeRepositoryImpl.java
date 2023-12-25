package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.Employee;
import com.solvd.laba.domain.enums.Position;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.EmployeeRepository;

import java.sql.*;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    @Override
    public void create(Employee employee, Long companyId, Long salaryId, Position position) throws SQLException {
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
    }

    @Override
    public void delete(Long employeeId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM employees e WHERE e.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Employee employee, Long employeeId) throws SQLException {
        /*Connection connection = CONNECTION_POOL.getConnection();
        String updateQuery = "UPDATE employees SET first_name = ?, last_name = ?, position = ?, company_id = ?, salary_id = ? WHERE id = ?";
        connection.setAutoCommit(false);

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getPosition().name());
            preparedStatement.setLong(4, employee.getId());
            preparedStatement.setLong(5, employee.getSalaryId());
            preparedStatement.setLong(6, employeeId);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Failed to update employee with ID: " + employeeId);
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException("Error updating employee with ID: " + employeeId, e);
        } finally {
            connection.setAutoCommit(true);
            CONNECTION_POOL.releaseConnection(connection);
        }*/
    }

    @Override
    public void addEmployeeToBuilding(Long employeeId, Long buildingId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertRelationship = "INSERT INTO employee_buildings (employee_id, building_id) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertRelationship)) {
            preparedStatement.setLong(1, employeeId);
            preparedStatement.setLong(2, buildingId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteEmployeeFromBuilding(Long employeeId, Long buildingId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM employee_buildings eb WHERE eb.employee_id = ? AND eb.building_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, employeeId);
            preparedStatement.setLong(2, buildingId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
