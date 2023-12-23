package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.CostEstimate;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.CostEstimateRepository;

import java.sql.*;

public class CostEstimateRepositoryIml implements CostEstimateRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public CostEstimate create(CostEstimate costEstimate, Long buildingId) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO cost_estimates (cost, building_id) VALUES (?, ?)";
        connection.setAutoCommit(false);

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBigDecimal(1, costEstimate.getCost());
            preparedStatement.setLong(2, buildingId);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                costEstimate.setId(resultSet.getLong(1));
            }

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
            CONNECTION_POOL.releaseConnection(connection);
        }
        return costEstimate;
    }

    @Override
    public void delete(Long costEstimateId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM cost_estimates ca WHERE ca.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, costEstimateId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Long getCostEstimateId(CostEstimate costEstimate) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "SELECT ca.id FROM cost_estimates ca WHERE ca.id = ?";
        connection.setReadOnly(true);

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setLong(1, costEstimate.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                costEstimate.setId(resultSet.getLong("id"));
                return resultSet.getLong("id");
            } else {
                throw new RuntimeException("Building record not found for the provided id.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setReadOnly(false);
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
