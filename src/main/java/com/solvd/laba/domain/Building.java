package com.solvd.laba.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Building {
    @XmlAttribute(name = "id")
    private Long id;
    private BuildingType buildingType;
    private String buildingDescription;
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
                ", costEstimate=" + costEstimate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(id, building.id) && Objects.equals(buildingType, building.buildingType)
                && Objects.equals(buildingDescription, building.buildingDescription)
                && Objects.equals(costEstimate, building.costEstimate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buildingType, buildingDescription, costEstimate);
    }
}
