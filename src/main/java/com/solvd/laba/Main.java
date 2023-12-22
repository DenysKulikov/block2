package com.solvd.laba;

import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.Employee;
import com.solvd.laba.domain.Salary;
import com.solvd.laba.domain.enums.Position;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.impl.CompanyRepositoryImpl;
import com.solvd.laba.persistence.impl.EmployeeRepositoryImpl;
import com.solvd.laba.persistence.impl.PositionRepositoryImpl;
import com.solvd.laba.persistence.impl.SalaryRepositoryImpl;
import com.solvd.laba.persistence.repositories.CompanyRepository;
import com.solvd.laba.persistence.repositories.EmployeeRepository;
import com.solvd.laba.persistence.repositories.PositionRepository;
import com.solvd.laba.persistence.repositories.SalaryRepository;
import com.solvd.laba.service.CompanyService;
import com.solvd.laba.service.PositionService;
import com.solvd.laba.service.SalaryService;
import com.solvd.laba.service.impl.CompanyServiceImpl;
import com.solvd.laba.service.impl.EmployeeServiceImpl;
import com.solvd.laba.service.impl.PositionServiceImpl;
import com.solvd.laba.service.impl.SalaryServiceImpl;

import java.math.BigDecimal;
import java.sql.*;

public class Main {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    public static void main(String[] args) throws SQLException {
        Company company = new Company();
        company.setName("Bud");

        Salary salary = new Salary();
        salary.setPosition(Position.BUILDER.name());
        salary.setAmount(BigDecimal.valueOf(100000.00));
        salary.setExperience("7 years");

        Employee employee = new Employee();
        employee.setFirstName("Alice");
        employee.setLastName("Johnson");
        employee.setPosition(Position.BUILDER);

        CompanyRepository companyRepository = new CompanyRepositoryImpl();
        CompanyService companyService = new CompanyServiceImpl(companyRepository);

        SalaryRepository salaryRepository = new SalaryRepositoryImpl();
        SalaryService salaryService = new SalaryServiceImpl(salaryRepository);

        PositionRepository positionRepository = new PositionRepositoryImpl();
        PositionService positionService = new PositionServiceImpl(positionRepository);

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeRepository, companyService, salaryService, positionService);
        employeeService.create(employee, company, salary, Position.BUILDER);

        /*Connection connection = CONNECTION_POOL.getConnection();
        String deleteCompany = "DELETE FROM companies c where c.company_name = 'Bud';";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteCompany)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }*/

        /*String deleteSalary = "DELETE FROM salaries s where s.amount = 100000.00;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSalary)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        String deleteEmployee = "DELETE FROM employees e where e.first_name = 'Alice';";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteEmployee)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }*/
    }
}