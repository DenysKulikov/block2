package com.solvd.laba.persistence.mybatisImpl;

import com.solvd.laba.domain.BuildingType;
import com.solvd.laba.persistence.Config;
import com.solvd.laba.persistence.repositories.BuildingTypeRepository;
import org.apache.ibatis.session.SqlSession;

public class BuildingTypeRepositoryMybatisImpl implements BuildingTypeRepository {
    @Override
    public void create(BuildingType buildingType) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BuildingTypeRepository buildingTypeRepository = sqlSession.getMapper(BuildingTypeRepository.class);
            buildingTypeRepository.create(buildingType);
        }
    }

    @Override
    public void delete(BuildingType buildingType) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            BuildingTypeRepository buildingTypeRepository = sqlSession.getMapper(BuildingTypeRepository.class);
            buildingTypeRepository.delete(buildingType);
        }
    }
}
