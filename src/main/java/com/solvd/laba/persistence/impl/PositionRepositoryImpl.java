package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.enums.Position;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.PositionRepository;

import java.sql.*;

public class PositionRepositoryImpl implements PositionRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    @Override
    public void crete(Position position) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO positions(position, has_car) VALUES (?, ?) ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto)) {
            preparedStatement.setString(1, position.name());
            preparedStatement.setBoolean(2, position.hasCar());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Position position) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM positions p WHERE p.position = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setString(1, position.name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
