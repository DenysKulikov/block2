package com.solvd.laba.persistence.mybatisImpl;

import com.solvd.laba.domain.Material;
import com.solvd.laba.persistence.Config;
import com.solvd.laba.persistence.repositories.MaterialRepository;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class MaterialRepositoryMybatisImpl implements MaterialRepository {
    @Override
    public void create(Material material) throws SQLException {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            MaterialRepository materialRepository = sqlSession.getMapper(MaterialRepository.class);
            materialRepository.create(material);
        }
    }

    @Override
    public void delete(Long materialId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            MaterialRepository materialRepository = sqlSession.getMapper(MaterialRepository.class);
            materialRepository.delete(materialId);
        }
    }

    @Override
    public Long getMaterialId(Material material) throws SQLException {
        return null;
    }

    @Override
    public void addMaterialToBuilding(Long materialId, Long buildingId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            MaterialRepository materialRepository = sqlSession.getMapper(MaterialRepository.class);
            materialRepository.addMaterialToBuilding(materialId, buildingId);
        }
    }

    @Override
    public void deleteMaterialFromBuilding(Long materialId, Long buildingId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            MaterialRepository materialRepository = sqlSession.getMapper(MaterialRepository.class);
            materialRepository.deleteMaterialFromBuilding(materialId, buildingId);
        }
    }
}
