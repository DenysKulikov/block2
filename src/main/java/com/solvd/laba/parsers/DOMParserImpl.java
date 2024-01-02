package com.solvd.laba.parsers;

import com.solvd.laba.domain.*;
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

public class DOMParserImpl {
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

            System.out.println("Company Name: " + company.getName());

            for (Employee employee : company.getEmployees()) {
                System.out.println("Employee ID: " + employee.getId());
                System.out.println("First Name: " + employee.getFirstName());
                System.out.println("Last Name: " + employee.getLastName());
                System.out.println("Position: " + employee.getPosition());

                for (Building building : employee.getBuildings()) {
                    System.out.println("Building ID: " + building.getId());
                    System.out.println("Building Type: " + building.getBuildingType().getType());
                    System.out.println("Base Cost: " + building.getBuildingType().getBaseCost());
                    System.out.println("Building Description: " + building.getBuildingDescription());

                    CostEstimate costEstimate = building.getCostEstimate();
                    System.out.println("Cost Estimate: " + costEstimate.getCost());
                }

                System.out.println("------------------------------------");
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Company parseCompany(Element companyElement) {
        Company company = new Company();
        company.setName(getTextContent(companyElement, "name"));

        NodeList employeeList = companyElement.getElementsByTagName("employee");
        for (int i = 0; i < employeeList.getLength(); i++) {
            Element employeeElement = (Element) employeeList.item(i);
            Employee employee = parseEmployee(employeeElement);
            company.addEmployee(employee);
        }

        return company;
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
        Element costEstimateElement = (Element) buildingElement.getElementsByTagName("costEstimateType").item(0);
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