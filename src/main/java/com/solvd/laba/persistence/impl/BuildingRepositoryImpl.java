package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.Building;
import com.solvd.laba.domain.BuildingType;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.BuildingRepository;

import java.sql.*;
import java.util.Optional;

public class BuildingRepositoryImpl implements BuildingRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    @Override
    public void create(Building building, Long companyId) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO buildings (building_type, building_description, company_id) VALUES (?, ?, ?)";
        connection.setAutoCommit(false);

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, building.getBuildingType().getType());
            preparedStatement.setString(2, building.getBuildingDescription());
            preparedStatement.setLong(3, companyId);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                building.setId(resultSet.getLong(1));
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
    public void delete(Long buildingId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM buildings b WHERE b.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, buildingId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Building> findById(Long buildingId) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "SELECT b.id, b.building_type, b.building_description FROM buildings b WHERE b.id = ?";
        connection.setReadOnly(true);

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setLong(1, buildingId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Building building = new Building();
                building.setId(resultSet.getLong("id"));
                building.setBuildingType(new BuildingType().setType(resultSet.getString("building_type")));
                building.setBuildingDescription(resultSet.getString("building_description"));
                return Optional.of(building);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setReadOnly(false);
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
