package com.solvd.laba.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Building {
    private Long id;
    private BuildingType buildingType;
    private String buildingDescription;
    private List<Employee> employees = new ArrayList<>();
    private CostEstimate costEstimate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public String getBuildingDescription() {
        return buildingDescription;
    }

    public void setBuildingDescription(String buildingDescription) {
        this.buildingDescription = buildingDescription;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public CostEstimate getCostEstimate() {
        return costEstimate;
    }

    public void setCostEstimate(CostEstimate costEstimate) {
        this.costEstimate = costEstimate;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", buildingType=" + buildingType +
                ", buildingDescription='" + buildingDescription + '\'' +
                ", employees=" + employees +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(id, building.id) && Objects.equals(buildingType, building.buildingType) && Objects.equals(buildingDescription, building.buildingDescription) && Objects.equals(employees, building.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buildingType, buildingDescription, employees);
    }
}
