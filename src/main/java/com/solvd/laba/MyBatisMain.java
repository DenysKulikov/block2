package com.solvd.laba;

import com.solvd.laba.domain.*;
import com.solvd.laba.persistence.impl.MaterialRepositoryImpl;
import com.solvd.laba.persistence.impl.MaterialTypeRepositoryImpl;
import com.solvd.laba.persistence.mybatisImpl.*;
import com.solvd.laba.persistence.repositories.*;
import com.solvd.laba.service.*;
import com.solvd.laba.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

public class MyBatisMain {
    private static final Logger LOGGER = LogManager.getLogger(MyBatisMain.class);

    public static void main(String[] args) throws SQLException {
        Company company = new Company();
        company.setName("Bud");

        Position position = new Position();
        position.setPositionName("BUILDER");
        position.setHasCar(false);

        Salary salary = new Salary();
        salary.setPosition(position.getPositionName());
        salary.setAmount(BigDecimal.valueOf(100000.00));
        salary.setExperience("7 years");

        Employee employee = new Employee();
        employee.setFirstName("Alice");
        employee.setLastName("Johnson");
        employee.setPosition(position.getPositionName());

        Payment payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(5000.00));
        payment.setPaymentDate(Date.valueOf("2023-01-15"));

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        BuildingType buildingType = new BuildingType();
        buildingType.setType("COMMERCIAL");
        buildingType.setBaseCost(BigDecimal.valueOf(180_000.00));

        Building building = new Building();
        building.setBuildingType(buildingType);
        building.setBuildingDescription("Commercial");

        BuildingApproval buildingApproval = new BuildingApproval();
        buildingApproval.setTimeNeeded("2 years");
        buildingApproval.setApprovedBy("Main constructor");

        CostEstimate costEstimate = new CostEstimate();
        costEstimate.setCost(BigDecimal.valueOf(1000000.00));

        MaterialType materialType = new MaterialType();
        materialType.setType("STEEL");

        Material material = new Material();
        material.setName("Nails");
        material.setAmount(100000L);
        material.setPrice(BigDecimal.valueOf(100000));
        material.setMaterialType(materialType);

        PositionRepository positionRepository = new PositionRepositoryMybatisImpl();
        PositionService positionService = new PositionServiceImpl(positionRepository);
        positionService.create(position);

        CompanyRepository companyRepository = new CompanyRepositoryMybatisIpl();
        CompanyService companyService = new CompanyServiceImpl(companyRepository);
        companyService.create(company);

        SalaryRepository salaryRepository = new SalaryRepositoryMybatisImpl();
        SalaryService salaryService = new SalaryServiceImpl(salaryRepository);
        salaryService.create(salary);

        EmployeeRepository employeeRepository = new EmployeeRepositoryMybatisImpl();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeRepository);
        employeeService.create(employee, company.getId(), salary.getId(), position.getPositionName());

        PaymentRepository paymentRepository = new PaymentRepositoryMybatisImpl();
        PaymentService paymentService = new PaymentServiceImpl(paymentRepository);
        paymentService.create(payment);

        CustomerRepository customerRepository = new CustomerRepositoryMybatisImpl();
        CustomerService customerService = new CustomerServiceImpl(customerRepository);
        customerService.create(customer, payment.getId());
        customerService.addCustomerToCompany(customer.getId(), company.getId());

        BuildingTypeRepository buildingTypeRepository = new BuildingTypeRepositoryMybatisImpl();
        BuildingTypeService buildingTypeService = new BuildingTypeServiceImpl(buildingTypeRepository);
        buildingTypeService.create(building.getBuildingType());

        BuildingRepository buildingRepository = new BuildingRepositoryMybatisImpl();
        BuildingService buildingService = new BuildingServiceImpl(buildingRepository);
        buildingService.create(building, company.getId());
        employeeService.addEmployeeToBuilding(employee.getId(), building.getId());

        BuildingApprovalRepository buildingApprovalRepository = new BuildingApprovalRepositoryMybatisImpl();
        BuildingApprovalService buildingApprovalService = new BuildingApprovalServiceImpl(buildingApprovalRepository);
        buildingApprovalService.create(buildingApproval, building.getId());

        CostEstimateRepository costEstimateRepository = new CostEstimateRepositoryMybatisImpl();
        CostEstimateService costEstimateService = new CostEstimateServiceImpl(costEstimateRepository);
        costEstimateService.create(costEstimate, building.getId());

        MaterialTypeRepository materialTypeRepository = new MaterialTypeRepositoryMybatisImpl();
        MaterialTypeService materialTypeService = new MaterialTypeServiceImpl(materialTypeRepository);
        materialTypeService.crete(material.getMaterialType());

        MaterialRepository materialRepository = new MaterialRepositoryMybatisImpl();
        MaterialService materialService = new MaterialServiceImpl(materialRepository);
        materialService.create(material);
        materialService.addMaterialToBuilding(material.getId(), building.getId());

        LOGGER.trace("Company id: " + company.getId());
        LOGGER.trace("Salary id: " + salary.getId());
        LOGGER.trace("Employee id: " + employee.getId());
        LOGGER.trace("Payment id: " + payment.getId());
        LOGGER.trace("Customer id: " + customer.getId());
        LOGGER.trace("Building id: " + building.getId());
        LOGGER.trace("Building Approval id: " + buildingApproval.getId());
        LOGGER.trace("CostEstimate (for building) id: " + costEstimate.getId());
        LOGGER.trace("Material id: " + material.getId());

        employeeRepository.deleteEmployeeFromBuilding(employee.getId(), building.getId());
        employeeRepository.delete(employee.getId());
        customerRepository.deleteCustomerFromCompany(customer.getId(), company.getId());
        customerRepository.delete(customer.getId());
        paymentRepository.delete(payment.getId());

        materialRepository.deleteMaterialFromBuilding(material.getId(), building.getId());
        materialRepository.delete(material.getId());
        materialTypeRepository.delete(materialType);
        buildingApprovalRepository.delete(buildingApproval.getId());
        costEstimateRepository.delete(costEstimate.getId());
        buildingRepository.delete(building.getId());
        buildingTypeRepository.delete(buildingType);

        companyService.delete(company.getId());
        salaryService.delete(salary.getId());
        positionService.delete(position);
    }
}
