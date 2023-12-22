package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.Salary;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.SalaryRepository;

import java.sql.*;

public class SalaryRepositoryImpl implements SalaryRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public Salary create(Salary salary) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO salaries(position, experience, amount) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, salary.getPosition());
            preparedStatement.setString(2, salary.getExperience());
            preparedStatement.setBigDecimal(3, salary.getAmount());

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                salary.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return salary;
    }

    @Override
    public Long getSalaryId(Salary salary) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO salaries(position, experience, amount) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, salary.getPosition());
            preparedStatement.setString(2, salary.getExperience());
            preparedStatement.setBigDecimal(3, salary.getAmount());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                salary.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return salary.getId();
    }
}
