package com.solvd.laba.persistence.mybatisImpl;

import com.solvd.laba.domain.CostEstimate;
import com.solvd.laba.persistence.Config;
import com.solvd.laba.persistence.repositories.CostEstimateRepository;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class CostEstimateRepositoryMybatisImpl implements CostEstimateRepository {
    @Override
    public void create(CostEstimate costEstimate, Long buildingId) throws SQLException {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CostEstimateRepository costEstimateRepository = sqlSession.getMapper(CostEstimateRepository.class);
            costEstimateRepository.create(costEstimate, buildingId);
        }
    }

    @Override
    public void delete(Long costEstimateId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            CostEstimateRepository costEstimateRepository = sqlSession.getMapper(CostEstimateRepository.class);
            costEstimateRepository.delete(costEstimateId);
        }
    }

    @Override
    public CostEstimate findById(Long costEstimateId) throws SQLException {
        return null;
    }
}
