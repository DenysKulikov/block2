package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.Payment;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.PaymentRepository;

import java.sql.*;

public class PaymentRepositoryImpl implements PaymentRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Payment payment) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO payments (amount, payment_date) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBigDecimal(1, payment.getAmount());
            preparedStatement.setDate(2, payment.getPaymentDate());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                payment.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long paymentId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM payments p WHERE p.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, paymentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Long getPaymentId(Payment payment) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "SELECT p.id FROM payments p WHERE p.id = ?";
        connection.setReadOnly(true);

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setLong(1, payment.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                payment.setId(resultSet.getLong("id"));
                return resultSet.getLong("id");
            } else {
                throw new RuntimeException("Payment record not found for the provided info.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setReadOnly(false);
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
