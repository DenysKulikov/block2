package com.solvd.laba.patterns.listener;

import com.solvd.laba.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String name;
    private List<Employee> employees;

    public Company() {
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        ListenersHolder.onNewEmployee(employee);
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        ListenersHolder.onEmployeeDismissal(employee);
        employees.remove(employee);
    }
}
