package com.solvd.laba;

import com.solvd.laba.domain.*;
import com.solvd.laba.domain.BuildingType;
import com.solvd.laba.domain.MaterialType;
import com.solvd.laba.domain.Position;
import com.solvd.laba.persistence.impl.*;
import com.solvd.laba.persistence.repositories.*;
import com.solvd.laba.service.*;
import com.solvd.laba.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
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

        CompanyRepository companyRepository = new CompanyRepositoryImpl();
        CompanyService companyService = new CompanyServiceImpl(companyRepository);

        SalaryRepository salaryRepository = new SalaryRepositoryImpl();
        SalaryService salaryService = new SalaryServiceImpl(salaryRepository);

        PositionRepository positionRepository = new PositionRepositoryImpl();
        PositionService positionService = new PositionServiceImpl(positionRepository);
        positionService.create(position);

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeRepository);
        companyService.create(company);
        salaryService.create(salary);
        employeeService.create(employee, company.getId(), salary.getId(), position.getPositionName());

        PaymentRepository paymentRepository = new PaymentRepositoryImpl();
        PaymentService paymentService = new PaymentServiceImpl(paymentRepository);
        paymentService.create(payment);

        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CustomerService customerService = new CustomerServiceImpl(customerRepository);
        customerService.create(customer, payment.getId());
        customerService.addCustomerToCompany(customer.getId(), company.getId());

        BuildingTypeRepository buildingTypeRepository = new BuildingTypeRepositoryImpl();
        BuildingTypeService buildingTypeService = new BuildingTypeServiceImpl(buildingTypeRepository);
        buildingTypeService.create(building.getBuildingType());

        BuildingRepository buildingRepository = new BuildingRepositoryImpl();
        BuildingService buildingService = new BuildingServiceImpl(buildingRepository);
        buildingService.create(building, company.getId());
        employeeService.addEmployeeToBuilding(employee.getId(), building.getId());

        BuildingApprovalRepository buildingApprovalRepository = new BuildingApprovalRepositoryImpl();
        BuildingApprovalService buildingApprovalService = new BuildingApprovalServiceImpl(buildingApprovalRepository);
        buildingApprovalService.create(buildingApproval, building.getId());

        CostEstimateRepository costEstimateRepository = new CostEstimateRepositoryIml();
        CostEstimateService costEstimateService = new CostEstimateServiceImpl(costEstimateRepository);
        costEstimateService.create(costEstimate, building.getId());

        MaterialTypeRepository materialTypeRepository = new MaterialTypeRepositoryImpl();
        MaterialTypeService materialTypeService = new MaterialTypeServiceImpl(materialTypeRepository);
        materialTypeService.crete(material.getMaterialType());

        MaterialRepository materialRepository = new MaterialRepositoryImpl();
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

        List<Company> companies = new ArrayList<>(companyService.retrieveAll());
        LOGGER.trace(companies);

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
        buildingTypeRepository.delete(building.getBuildingType());

        companyRepository.delete(company.getId());
        salaryRepository.delete(salary.getId());
        positionRepository.delete(position);
    }
}