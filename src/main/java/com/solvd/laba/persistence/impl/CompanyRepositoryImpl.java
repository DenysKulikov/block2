package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.Employee;
import com.solvd.laba.domain.Salary;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.CompanyRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyRepositoryImpl implements CompanyRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String FIND_ALL_QUERY = "Select " +
            "c.id AS company_id, c.company_name AS company_name, " +
            "e.id AS employee_id, e.first_name, e.last_name, e.position, " +
            "s.id AS salary_id, s.experience AS employee_experience, s.amount AS salary_amount, " +
            "p.has_car AS does_employee_has_car " +
            "FROM companies c " +
            "LEFT JOIN employees e ON c.id = e.company_id " +
            "LEFT JOIN salaries s ON e.salary_id = s.id " +
            "LEFT JOIN positions p ON e.position = p.position ";

    @Override
    public Company create(Company company) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String insertInto = "INSERT INTO companies(company_name) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInto, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, company.getName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                company.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return company;
    }

    @Override
    public List<Company> findAll() {
        List<Company> companies;

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            companies = mapCompanies(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find all companies.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return companies;
    }

    public Long getCompanyId(Company company) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "SELECT c.id FROM companies c WHERE c.company_name = ?";
        connection.setReadOnly(true);

        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setString(1, company.getName());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                company.setId(resultSet.getLong("id"));
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

    private List<Company> mapCompanies(ResultSet resultSet) throws SQLException {
        List<Company> companies = new ArrayList<>();
        Map<Long, Company> companyMap = new HashMap<>();

        while (resultSet.next()) {
            long companyId = resultSet.getLong("company_id");
            Company company = companyMap.get(companyId);

            if (company == null) {
                company = new Company();
                company.setId(companyId);
                company.setName(resultSet.getString("company_name"));
                companyMap.put(companyId, company);
                companies.add(company);
            }

            long employeeId = resultSet.getLong("employee_id");
            if (employeeId != 0) {
                Employee employee = new Employee();
                employee.setId(employeeId);
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));

                long salaryId = resultSet.getLong("salary_id");
                if (salaryId != 0) {
                    Salary salary = new Salary();
                    salary.setId(salaryId);
                    salary.setExperience(resultSet.getString("employee_experience"));
                    salary.setAmount(resultSet.getBigDecimal("salary_amount"));
                }

                boolean hasCar = resultSet.getBoolean("does_employee_has_car");
                employee.setHasCar(hasCar);

                company.addEmployee(employee);
            }
        }

        return companies;
    }
}
