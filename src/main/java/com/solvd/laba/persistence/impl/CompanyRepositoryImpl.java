package com.solvd.laba.persistence.impl;

import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.Employee;
import com.solvd.laba.domain.Salary;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.repositories.CompanyRepository;

import java.sql.*;
import java.util.*;

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
    public void create(Company company) throws SQLException {
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
    }

    @Override
    public void delete(Long companyId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "DELETE FROM companies c WHERE c.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, companyId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Company company, Long companyId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String updateQuery = "UPDATE companies SET company_name = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, company.getName());
            preparedStatement.setLong(2, companyId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No company found with id: " + companyId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
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

    @Override
    public Optional<Company> findById(Long companyId) throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "SELECT c.id, c.company_name FROM companies c WHERE c.id = ?";
        connection.setReadOnly(true);

        try (PreparedStatement preparedStatement = connection.prepareStatement(select, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, companyId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Company company = new Company();
                company.setId(resultSet.getLong("id"));
                company.setName(resultSet.getString("company_name"));
                return Optional.of(company);
            } else {
                return Optional.empty();
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

                company.addEmployee(employee);
            }
        }

        return companies;
    }
}
