package com.solvd.laba.parsers;

import com.solvd.laba.domain.Building;
import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.Employee;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXParserImpl {
    public static void main(String[] args) {
        File file = new File("src/main/resources/company.xml");

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            CompanyHandler handler = new CompanyHandler();
            saxParser.parse(file, handler);

            Company company = handler.getCompany();

            System.out.println("Company Name: " + company.getName());

            for (Employee employee : company.getEmployees()) {
                System.out.println("Employee ID: " + employee.getId());
                System.out.println("First Name: " + employee.getFirstName());
                System.out.println("Last Name: " + employee.getLastName());

                String position = employee.getPosition();
                System.out.println("Position: " + position);

                for (Building building : employee.getBuildings()) {
                    System.out.println("Building ID: " + building.getId());
                    System.out.println("Building Type: " + building.getBuildingType().getType());
                    System.out.println("Base Cost: " + building.getBuildingType().getBaseCost());
                    System.out.println("Building Description: " + building.getBuildingDescription());
                }

                System.out.println("------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
