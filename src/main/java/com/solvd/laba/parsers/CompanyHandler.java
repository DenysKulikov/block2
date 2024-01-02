package com.solvd.laba.parsers;

import com.solvd.laba.domain.Building;
import com.solvd.laba.domain.BuildingType;
import com.solvd.laba.domain.Company;
import com.solvd.laba.domain.Employee;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;

class CompanyHandler extends DefaultHandler {
    private Company company;
    private Employee currentEmployee;
    private Building currentBuilding;
    private BuildingType currentBuildingType;
    private StringBuilder currentText;

    public Company getCompany() {
        return company;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentText = new StringBuilder();

        switch (qName) {
            case "company":
                company = new Company();
                break;
            case "employee":
                currentEmployee = new Employee();
                String employeeId = attributes.getValue("id");
                currentEmployee.setId(employeeId != null && !employeeId.isEmpty() ? Long.parseLong(employeeId) : null);
                break;
            case "building":
                currentBuilding = new Building();
                String buildingId = attributes.getValue("id");
                currentBuilding.setId(buildingId != null && !buildingId.isEmpty() ? Long.parseLong(buildingId) : null);
                break;
            case "buildingType":
                currentBuildingType = new BuildingType();
                String buildingTypeId = attributes.getValue("id");
                currentBuildingType.setId(buildingTypeId != null && !buildingTypeId.isEmpty() ? Long.parseLong(buildingTypeId) : null);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currentText.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String text = currentText.toString().trim();

        switch (qName) {
            case "name":
                company.setName(text);
                break;
            case "firstName":
                currentEmployee.setFirstName(text);
                break;
            case "lastName":
                currentEmployee.setLastName(text);
                break;
            case "position":
                currentEmployee.setPosition(text);
                break;
            case "hasCar":
                currentEmployee.setHasCar(Boolean.parseBoolean(text));
                break;
            case "employee":
                company.addEmployee(currentEmployee);
                break;
            case "type":
                currentBuildingType.setType(text);
                break;
            case "baseCost":
                currentBuildingType.setBaseCost(new BigDecimal(text));
                break;
            case "buildingType":
                currentBuilding.setBuildingType(currentBuildingType);
                break;
            case "buildingDescription":
                currentBuilding.setBuildingDescription(text);
                break;
            case "building":
                currentEmployee.addBuilding(currentBuilding);
                break;
        }
    }
}
