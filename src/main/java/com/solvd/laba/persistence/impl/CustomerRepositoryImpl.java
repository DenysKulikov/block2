package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.Customer;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.CustomerRepository;

import java.sql.*;

public class CustomerRepositoryImpl implements CustomerRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Customer customer, Long paymentId) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO customers (first_name, last_name, payment_id) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setLong(3, paymentId);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                customer.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long customerId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM customers c WHERE c.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, customerId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Long getCustomerId(Customer customer) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "SELECT c.id FROM customers c WHERE c.id = ?";
        connection.setReadOnly(true);

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setLong(1, customer.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer.setId(resultSet.getLong("id"));
                return resultSet.getLong("id");
            } else {
                throw new RuntimeException("Company record not found for the provided company name.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setReadOnly(false);
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void addCustomerToCompany(Long customerId, Long companyId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertRelationship = "INSERT INTO customer_companies (customer_id, company_id) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertRelationship)) {
            preparedStatement.setLong(1, customerId);
            preparedStatement.setLong(2, companyId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteCustomerFromCompany(Long customerId, Long companyId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM customer_companies cc WHERE cc.customer_id = ? AND cc.company_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, customerId);
            preparedStatement.setLong(2, companyId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
