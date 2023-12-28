package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.BuildingApproval;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.BuildingApprovalRepository;

import java.sql.*;

public class BuildingApprovalRepositoryImpl implements BuildingApprovalRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(BuildingApproval buildingApproval, Long buildingId) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO building_approvals (time_needed, approved_by, building_id) VALUES (?, ?, ?)";
        connection.setAutoCommit(false);

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, buildingApproval.getTimeNeeded());
            preparedStatement.setString(2, buildingApproval.getApprovedBy());
            preparedStatement.setLong(3, buildingId);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                buildingApproval.setId(resultSet.getLong(1));
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
    public void delete(Long buildingApprovalId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM building_approvals ba WHERE ba.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, buildingApprovalId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public BuildingApproval findById(Long buildingApprovalId) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "SELECT ba.id, ba.time_needed, ba.approved_by FROM building_approvals ba WHERE ba.id = ?";
        connection.setReadOnly(true);

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setLong(1, buildingApprovalId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BuildingApproval buildingApproval = new BuildingApproval();
                buildingApproval.setId(resultSet.getLong("id"));
                buildingApproval.setTimeNeeded(resultSet.getString("time_needed"));
                buildingApproval.setApprovedBy(resultSet.getString("approved_by"));
                return buildingApproval;
            } else {
                throw new RuntimeException("Building Approval record not found for the provided id.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setReadOnly(false);
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
