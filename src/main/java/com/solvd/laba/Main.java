package com.solvd.laba;

import com.solvd.laba.domain.*;
import com.solvd.laba.domain.enums.BuildingType;
import com.solvd.laba.domain.enums.MaterialType;
import com.solvd.laba.domain.enums.Position;
import com.solvd.laba.persistence.impl.*;
import com.solvd.laba.persistence.repositories.*;
import com.solvd.laba.service.*;
import com.solvd.laba.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
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

        Payment payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(5000.00));
        payment.setPaymentDate(Date.valueOf("2023-01-15"));

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        Building building = new Building();
        building.setBuildingType(BuildingType.COMMERCIAL);
        building.setBuildingDescription("Commercial");

        BuildingApproval buildingApproval = new BuildingApproval();
        buildingApproval.setTime_needed("2 years");
        buildingApproval.setApproved_by("Main constructor");

        CostEstimate costEstimate = new CostEstimate();
        costEstimate.setCost(BigDecimal.valueOf(1000000.00));

        Material material = new Material();
        material.setName("Nails");
        material.setAmount(100000L);
        material.setPrice(BigDecimal.valueOf(100000));
        material.setMaterialType(MaterialType.STEEL);

        CompanyRepository companyRepository = new CompanyRepositoryImpl();
        CompanyService companyService = new CompanyServiceImpl(companyRepository);

        SalaryRepository salaryRepository = new SalaryRepositoryImpl();
        SalaryService salaryService = new SalaryServiceImpl(salaryRepository);

        PositionRepository positionRepository = new PositionRepositoryImpl();
        PositionService positionService = new PositionServiceImpl(positionRepository);

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeRepository, companyService, salaryService, positionService);
        companyService.create(company);
        salaryService.create(salary);
        employeeService.create(employee, company.getId(), salary.getId(), Position.BUILDER);

        PaymentRepository paymentRepository = new PaymentRepositoryImpl();
        PaymentService paymentService = new PaymentServiceImpl(paymentRepository);
        paymentService.create(payment);

        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CustomerService customerService = new CustomerServiceImpl(customerRepository);
        customerService.create(customer, payment.getId());
        customerService.addCustomerToCompany(customer.getId(), company.getId());

        BuildingTypeRepository buildingTypeRepository = new BuildingTypeRepositoryImpl();
        BuildingTypeService buildingTypeService = new BuildingTypeServiceImpl(buildingTypeRepository);
        buildingTypeService.crete(BuildingType.COMMERCIAL);

        BuildingRepository buildingRepository = new BuildingRepositoryImpl();
        BuildingService buildingService = new BuildingServiceImpl(buildingRepository);
        buildingService.create(building, company.getId());

        BuildingApprovalRepository buildingApprovalRepository = new BuildingApprovalRepositoryImpl();
        BuildingApprovalService buildingApprovalService = new BuildingApprovalServiceImpl(buildingApprovalRepository);
        buildingApprovalService.create(buildingApproval, building.getId());

        CostEstimateRepository costEstimateRepository = new CostEstimateRepositoryIml();
        CostEstimateService costEstimateService = new CostEstimateServiceImpl(costEstimateRepository);
        costEstimateService.create(costEstimate, building.getId());

        MaterialTypeRepository materialTypeRepository = new MaterialTypeRepositoryImpl();
        MaterialTypeService materialTypeService = new MaterialTypeServiceImpl(materialTypeRepository);
        materialTypeService.crete(MaterialType.STEEL);

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
        LOGGER.trace("Material Approval id: " + material.getId());

        employeeRepository.delete(employee.getId());
        customerRepository.deleteCustomerFromCompany(customer.getId(), company.getId());
        customerRepository.delete(customer.getId());
        paymentRepository.delete(payment.getId());

        materialRepository.deleteMaterialFromBuilding(material.getId(), building.getId());
        materialRepository.delete(material.getId());
        materialTypeRepository.delete(MaterialType.STEEL);
        buildingApprovalRepository.delete(buildingApproval.getId());
        costEstimateRepository.delete(costEstimate.getId());
        buildingRepository.delete(building.getId());
        buildingTypeRepository.delete(building.getBuildingType());

        companyRepository.delete(company.getId());
        salaryRepository.delete(salary.getId());
        positionRepository.delete(Position.BUILDER);
    }
}