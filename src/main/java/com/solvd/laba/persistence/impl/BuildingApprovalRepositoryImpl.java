package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.BuildingApproval;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.BuildingApprovalRepository;

import java.sql.*;

public class BuildingApprovalRepositoryImpl implements BuildingApprovalRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public BuildingApproval create(BuildingApproval buildingApproval, Long buildingId) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO building_approvals (time_needed, approved_by, building_id) VALUES (?, ?, ?);";
        connection.setAutoCommit(false);

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, buildingApproval.getTime_needed());
            preparedStatement.setString(2, buildingApproval.getApproved_by());
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
        return buildingApproval;
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
    public Long getBuildingApprovalId(BuildingApproval buildingApproval) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "SELECT ba.id FROM building_approvals ba WHERE ba.id = ?";
        connection.setReadOnly(true);

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setLong(1, buildingApproval.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                buildingApproval.setId(resultSet.getLong("id"));
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
