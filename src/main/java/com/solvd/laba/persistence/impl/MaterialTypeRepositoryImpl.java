package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.MaterialType;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.MaterialTypeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaterialTypeRepositoryImpl implements MaterialTypeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    @Override
    public void crete(MaterialType materialType) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO material_types (material_type) VALUES (?) ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto)) {
            preparedStatement.setString(1, materialType.getType());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(MaterialType materialType) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM material_types mt WHERE mt.material_type = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setString(1, materialType.getType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
