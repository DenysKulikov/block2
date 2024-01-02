package com.solvd.laba.persistence.mybatisImpl;

import com.solvd.laba.domain.MaterialType;
import com.solvd.laba.persistence.Config;
import com.solvd.laba.persistence.repositories.MaterialTypeRepository;
import org.apache.ibatis.session.SqlSession;

public class MaterialTypeRepositoryMybatisImpl implements MaterialTypeRepository {
    @Override
    public void create(MaterialType materialType) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            MaterialTypeRepository materialTypeRepository = sqlSession.getMapper(MaterialTypeRepository.class);
            materialTypeRepository.create(materialType);
        }
    }

    @Override
    public void delete(MaterialType materialType) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            MaterialTypeRepository materialTypeRepository = sqlSession.getMapper(MaterialTypeRepository.class);
            materialTypeRepository.delete(materialType);
        }
    }
}
