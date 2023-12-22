package com.solvd.laba.domain;

import com.solvd.laba.domain.enums.Position;

public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private Position position;
    private long companyId;
    private long salaryId;
    private boolean hasCar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(long salaryId) {
        this.salaryId = salaryId;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }
}
