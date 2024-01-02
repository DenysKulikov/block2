package com.solvd.laba.persistence.mybatisImpl;

import com.solvd.laba.domain.BuildingApproval;
import com.solvd.laba.persistence.Config;
import com.solvd.laba.persistence.repositories.BuildingApprovalRepository;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class BuildingApprovalRepositoryMybatisImpl implements BuildingApprovalRepository {
    @Override
    public void create(BuildingApproval buildingApproval, Long buildingId) throws SQLException {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BuildingApprovalRepository buildingApprovalRepository = sqlSession.getMapper(BuildingApprovalRepository.class);
            buildingApprovalRepository.create(buildingApproval, buildingId);
        }
    }

    @Override
    public void delete(Long buildingApprovalId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BuildingApprovalRepository buildingApprovalRepository = sqlSession.getMapper(BuildingApprovalRepository.class);
            buildingApprovalRepository.delete(buildingApprovalId);
        }
    }

    @Override
    public BuildingApproval findById(Long buildingApprovalId) throws SQLException {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BuildingApprovalRepository buildingApprovalRepository = sqlSession.getMapper(BuildingApprovalRepository.class);
            return buildingApprovalRepository.findById(buildingApprovalId);
        }
    }
}
