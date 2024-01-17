package com.solvd.laba.patterns.builder;

import com.solvd.laba.domain.Building;

import java.math.BigDecimal;

public class Employee {
    private String firstName;
    private String lastName;
    private int age;
    private BigDecimal salary;
    private String position;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    public static Builder builder() {
        return new Builder(new Employee());
    }

    public static class Builder {
        private final Employee employee;

        public Builder(Employee employee) {
            this.employee = employee;
        }

        public Builder firstName(String firstName) {
            employee.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            employee.lastName = lastName;
            return this;
        }

        public Builder age(int age) {
            employee.age = age;
            return this;
        }

        public Builder salary(BigDecimal salary) {
            employee.salary = salary;
            return this;
        }

        public Builder position(String position) {
            employee.position = position;
            return this;
        }

        public Employee build() {
            return employee;
        }
    }
}
