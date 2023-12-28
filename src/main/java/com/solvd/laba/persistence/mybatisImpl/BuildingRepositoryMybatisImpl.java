package com.solvd.laba.persistence.mybatisImpl;

import com.solvd.laba.domain.Building;
import com.solvd.laba.persistence.Config;
import com.solvd.laba.persistence.repositories.BuildingRepository;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.Optional;

public class BuildingRepositoryMybatisImpl implements BuildingRepository {
    @Override
    public void create(Building building, Long companyId) throws SQLException {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BuildingRepository buildingRepository = sqlSession.getMapper(BuildingRepository.class);
            buildingRepository.create(building, companyId);
        }
    }

    @Override
    public void delete(Long buildingId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BuildingRepository buildingRepository = sqlSession.getMapper(BuildingRepository.class);
            buildingRepository.delete(buildingId);
        }
    }

    @Override
    public Optional<Building> findById(Long buildingId) throws SQLException {
        return Optional.empty();
    }
}
