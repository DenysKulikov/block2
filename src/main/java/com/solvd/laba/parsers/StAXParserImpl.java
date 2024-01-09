package com.solvd.laba.parsers;

import com.solvd.laba.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.*;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;

public class StAXParserImpl {
    private static final Logger LOGGER = LogManager.getLogger(StAXParserImpl.class);

    public static void main(String[] args) {
        File file = new File("src/main/resources/company.xml");

        try (FileInputStream inputStream = new FileInputStream(file)) {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);

            Company company = parseCompany(xmlStreamReader);

            LOGGER.trace("Company Name: " + company.getName());

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
                }

                LOGGER.trace("------------------------------------");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Long parseLongAttribute(String value) {
        return (value != null && !value.isEmpty()) ? Long.parseLong(value) : null;
    }

    private static Company parseCompany(XMLStreamReader xmlStreamReader) throws XMLStreamException {
        Company company = null;
        Employee currentEmployee = null;
        Building currentBuilding = null;
        BuildingType currentBuildingType = null;
        StringBuilder currentText = new StringBuilder();

        while (xmlStreamReader.hasNext()) {
            int event = xmlStreamReader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = xmlStreamReader.getLocalName();
                    switch (elementName) {
                        case "company":
                            company = new Company();
                            break;
                        case "name":
                            company.setName(xmlStreamReader.getElementText());
                            break;
                        case "employee":
                            currentEmployee = new Employee();
                            currentEmployee.setId(parseLongAttribute(xmlStreamReader.getAttributeValue(null, "id")));
                            break;
                        case "firstName":
                            currentText.setLength(0);
                            currentEmployee.setFirstName(xmlStreamReader.getElementText());
                            break;
                        case "lastName":
                            currentText.setLength(0);
                            currentEmployee.setLastName(xmlStreamReader.getElementText());
                            break;
                        case "position":
                            currentText.setLength(0);
                            currentEmployee.setPosition(xmlStreamReader.getElementText());
                            break;
                        case "building":
                            currentBuilding = new Building();
                            currentBuilding.setId(parseLongAttribute(xmlStreamReader.getAttributeValue(null, "id")));
                            currentBuildingType = new BuildingType();
                            break;
                        case "buildingType":
                            currentBuildingType = new BuildingType();
                            currentBuildingType.setId(parseLongAttribute(xmlStreamReader.getAttributeValue(null, "id")));
                            break;
                        case "type":
                            currentText.setLength(0);
                            currentBuildingType.setType(xmlStreamReader.getElementText());
                            break;
                        case "baseCost":
                            currentText.setLength(0);
                            currentBuildingType.setBaseCost(new BigDecimal(xmlStreamReader.getElementText()));
                            break;
                        case "buildingDescription":
                            currentText.setLength(0);
                            currentBuilding.setBuildingDescription(xmlStreamReader.getElementText());
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = xmlStreamReader.getLocalName();
                    switch (endElementName) {
                        case "employee":
                            company.addEmployee(currentEmployee);
                            break;
                        case "building":
                            currentBuilding.setBuildingType(currentBuildingType);
                            currentEmployee.addBuilding(currentBuilding);
                            break;
                    }
                    break;
            }
        }

        return company;
    }
}
