package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.enums.MaterialType;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.MaterialTypeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaterialTypeRepositoryImpl implements MaterialTypeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    @Override
    public MaterialType crete(MaterialType materialType) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO material_types (material_type) VALUES (?) ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto)) {
            preparedStatement.setString(1, materialType.name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return materialType;
    }

    @Override
    public void delete(MaterialType materialType) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM material_types mt WHERE mt.material_type = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setString(1, materialType.name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
