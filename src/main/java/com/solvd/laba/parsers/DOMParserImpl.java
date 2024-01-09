package com.solvd.laba.parsers;

import com.solvd.laba.Main;
import com.solvd.laba.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

public class DOMParserImpl {
    private static final Logger LOGGER = LogManager.getLogger(DOMParserImpl.class);

    public static void main(String[] args) {
        File file = new File("src/main/resources/company.xml");

        // DOM
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList companyList = document.getElementsByTagName("company");
            Element companyElement = (Element) companyList.item(0);

            Company company = parseCompany(companyElement);

            LOGGER.trace("Company id: " + company.getId());
            LOGGER.trace("Company Name: " + company.getName());

            for (Customer customer : company.getCustomers()) {
                LOGGER.trace("Customer ID: " + customer.getId());
                LOGGER.trace("First Name: " + customer.getFirstName());
                LOGGER.trace("Last Name: " + customer.getLastName());
                LOGGER.trace(customer.getPayment());
            }

            for (Employee employee : company.getEmployees()) {
                LOGGER.trace("Employee ID: " + employee.getId());
                LOGGER.trace("First Name: " + employee.getFirstName());
                LOGGER.trace("Last Name: " + employee.getLastName());
                LOGGER.trace("Position: " + employee.getPosition());

                for (Building building : employee.getBuildings()) {
                    LOGGER.trace("Building ID: " + building.getId());
                    LOGGER.trace("Building Type: " + building.getBuildingType().getType());
                    LOGGER.trace("Base Cost: " + building.getBuildingType().getBaseCost());
                    LOGGER.trace("Building Description: " + building.getBuildingDescription());

                    CostEstimate costEstimate = building.getCostEstimate();
                    LOGGER.trace("Cost Estimate: " + costEstimate.getCost());
                }

                LOGGER.trace("------------------------------------");
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Company parseCompany(Element companyElement) {
        Company company = new Company();
        company.setId(Long.parseLong(companyElement.getAttribute("id")));
        company.setName(getTextContent(companyElement, "name"));

        NodeList employeeList = companyElement.getElementsByTagName("employee");
        for (int i = 0; i < employeeList.getLength(); i++) {
            Element employeeElement = (Element) employeeList.item(i);
            Employee employee = parseEmployee(employeeElement);
            company.addEmployee(employee);
        }

        NodeList customerList = companyElement.getElementsByTagName("customer");
        for (int i = 0; i < customerList.getLength(); i++) {
            Element customerElement = (Element) customerList.item(i);
            Customer customer = parseCustomer(customerElement);
            company.addCustomer(customer);
        }

        return company;
    }

    private static Customer parseCustomer(Element customerElement) {
        Customer customer = new Customer();
        customer.setId(Long.parseLong(customerElement.getAttribute("id")));
        customer.setFirstName(getTextContent(customerElement, "firstName"));
        customer.setLastName(getTextContent(customerElement, "lastName"));

        Element paymentElement = (Element) customerElement.getElementsByTagName("payment").item(0);
        Payment payment = parsePayment(paymentElement);
        customer.setPayment(payment);

        return customer;
    }

    private static Payment parsePayment(Element paymentElement) {
        Payment payment = new Payment();
        payment.setId(Long.parseLong(paymentElement.getAttribute("id")));
        payment.setAmount(new BigDecimal(getTextContent(paymentElement, "amount")));
        payment.setPaymentDate(Date.valueOf(getTextContent(paymentElement, "paymentDate")));

        return payment;
    }

    private static Employee parseEmployee(Element employeeElement) {
        Employee employee = new Employee();
        employee.setId(Long.parseLong(employeeElement.getAttribute("id")));
        employee.setFirstName(getTextContent(employeeElement, "firstName"));
        employee.setLastName(getTextContent(employeeElement, "lastName"));
        employee.setPosition(getTextContent(employeeElement, "position"));

        NodeList buildingList = employeeElement.getElementsByTagName("building");
        for (int i = 0; i < buildingList.getLength(); i++) {
            Element buildingElement = (Element) buildingList.item(i);
            Building building = parseBuilding(buildingElement);
            employee.addBuilding(building);
        }

        return employee;
    }

    private static Building parseBuilding(Element buildingElement) {
        Building building = new Building();
        building.setId(parseLongAttribute(buildingElement, "id"));

        BuildingType buildingType = new BuildingType();
        Element buildingTypeElement = (Element) buildingElement.getElementsByTagName("buildingType").item(0);
        buildingType.setType(getTextContent(buildingTypeElement, "type"));
        buildingType.setBaseCost(new BigDecimal(getTextContent(buildingTypeElement, "baseCost")));
        building.setBuildingType(buildingType);

        building.setBuildingDescription(getTextContent(buildingElement, "buildingDescription"));

        CostEstimate costEstimate = new CostEstimate();
        Element costEstimateElement = (Element) buildingElement.getElementsByTagName("costEstimate").item(0);
        costEstimate.setCost(new BigDecimal(getTextContent(costEstimateElement, "cost")));
        building.setCostEstimate(costEstimate);

        return building;
    }

    private static Long parseLongAttribute(Element element, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        return attributeValue.isEmpty() ? null : Long.parseLong(attributeValue);
    }

    private static String getTextContent(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}