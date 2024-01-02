package com.solvd.laba.persistence.mybatisImpl;

import com.solvd.laba.domain.Payment;
import com.solvd.laba.persistence.Config;
import com.solvd.laba.persistence.repositories.EmployeeRepository;
import com.solvd.laba.persistence.repositories.PaymentRepository;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class PaymentRepositoryMybatisImpl implements PaymentRepository {
    @Override
    public void create(Payment payment) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            PaymentRepository paymentRepository = sqlSession.getMapper(PaymentRepository.class);
            paymentRepository.create(payment);
        }
    }

    @Override
    public void delete(Long salaryId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            PaymentRepository paymentRepository = sqlSession.getMapper(PaymentRepository.class);
            paymentRepository.delete(salaryId);
        }
    }

    @Override
    public Long getPaymentId(Payment payment) throws SQLException {
        return null;
    }
}
