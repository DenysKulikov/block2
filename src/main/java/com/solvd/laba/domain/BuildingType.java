package com.solvd.laba.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildingType {
    private Long id;
    private String type;
    private BigDecimal baseCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public BuildingType setType(String type) {
        this.type = type;
        return this;
    }

    public BigDecimal getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(BigDecimal baseCost) {
        this.baseCost = baseCost;
    }

    @Override
    public String toString() {
        return "BuildingType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", baseCost=" + baseCost +
                '}';
    }
}
