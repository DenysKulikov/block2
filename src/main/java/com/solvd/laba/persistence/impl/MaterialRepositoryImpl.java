package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.Material;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.MaterialRepository;

import java.sql.*;

public class MaterialRepositoryImpl implements MaterialRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Material material) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO materials (material_name, amount, price, material_type) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, material.getName());
            preparedStatement.setLong(2, material.getAmount());
            preparedStatement.setBigDecimal(3, material.getPrice());
            preparedStatement.setString(4, material.getMaterialType().name());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                material.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long materialId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM materials m WHERE m.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, materialId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Long getMaterialId(Material material) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "SELECT m.id FROM materials m WHERE m.id = ?";
        connection.setReadOnly(true);

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setLong(1, material.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                material.setId(resultSet.getLong("id"));
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
    public void addMaterialToBuilding(Long materialId, Long buildingId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertRelationship = "INSERT INTO material_buildings (material_id, building_id) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertRelationship)) {
            preparedStatement.setLong(1, materialId);
            preparedStatement.setLong(2, buildingId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteMaterialFromBuilding(Long materialId, Long buildingId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM material_buildings mb WHERE mb.material_id = ? AND mb.building_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, materialId);
            preparedStatement.setLong(2, buildingId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
