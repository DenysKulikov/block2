package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.enums.BuildingType;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.BuildingTypeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BuildingTypeRepositoryImpl implements BuildingTypeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    @Override
    public void crete(BuildingType buildingType) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO building_types (building_type, base_cost) VALUES (?, ?) ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto)) {
            preparedStatement.setString(1, buildingType.name());
            preparedStatement.setBigDecimal(2, buildingType.getBaseCost());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(BuildingType buildingType) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM building_types bt WHERE bt.building_type = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setString(1, buildingType.name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
