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
    public CostEstimate findById(Long costEstimateId) throws SQLException {
        return null;
    }
}
