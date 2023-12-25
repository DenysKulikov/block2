package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.Salary;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.SalaryRepository;

import java.sql.*;

public class SalaryRepositoryImpl implements SalaryRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Salary salary) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO salaries(position, experience, amount) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, salary.getPosition());
            preparedStatement.setString(2, salary.getExperience());
            preparedStatement.setBigDecimal(3, salary.getAmount());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                salary.setId(resultSet.getLong(1));
            } else {
                throw new RuntimeException("Failed to get generated key for Salary.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long salaryId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM salaries s WHERE s.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, salaryId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Long getSalaryId(Salary salary) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "SELECT s.id FROM salaries s WHERE s.experience = ?";
        connection.setReadOnly(true);

        try (PreparedStatement preparedStatement = connection.prepareStatement(select, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, salary.getExperience());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                salary.setId(resultSet.getLong("id"));
                return resultSet.getLong("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setReadOnly(false);
            CONNECTION_POOL.releaseConnection(connection);
        }
        return salary.getId();
    }
}
