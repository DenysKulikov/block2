package com.solvd.laba.persistence.mybatisImpl;


import com.solvd.laba.domain.Position;
import com.solvd.laba.persistence.Config;
import com.solvd.laba.persistence.repositories.PositionRepository;
import org.apache.ibatis.session.SqlSession;

public class PositionRepositoryMybatisImpl implements PositionRepository {
    @Override
    public void create(Position position) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            PositionRepository positionRepository = sqlSession.getMapper(PositionRepository.class);
            positionRepository.create(position);
        }
    }

    @Override
    public void delete(Position position) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            PositionRepository positionRepository = sqlSession.getMapper(PositionRepository.class);
            positionRepository.delete(position);
        }
    }
}
