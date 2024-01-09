package com.solvd.laba.parsers;

import com.solvd.laba.domain.Building;
import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXParserImpl {
    private static final Logger LOGGER = LogManager.getLogger(SAXParserImpl.class);
    public static void main(String[] args) {
        File file = new File("src/main/resources/company.xml");

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            CompanyHandler handler = new CompanyHandler();
            saxParser.parse(file, handler);

            Company company = handler.getCompany();

            LOGGER.trace("Company Name: " + company.getName());

            for (Employee employee : company.getEmployees()) {
                LOGGER.trace("Employee ID: " + employee.getId());
                LOGGER.trace("First Name: " + employee.getFirstName());
                LOGGER.trace("Last Name: " + employee.getLastName());

                String position = employee.getPosition();
                LOGGER.trace("Position: " + position);

                for (Building building : employee.getBuildings()) {
                    LOGGER.trace("Building ID: " + building.getId());
                    LOGGER.trace("Building Type: " + building.getBuildingType().getType());
                    LOGGER.trace("Base Cost: " + building.getBuildingType().getBaseCost());
                    LOGGER.trace("Building Description: " + building.getBuildingDescription());
                }

                LOGGER.trace("------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
