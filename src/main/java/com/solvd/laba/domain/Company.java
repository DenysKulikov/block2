package com.solvd.laba.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {
    @XmlAttribute(name = "id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private List<Employee> employees = new ArrayList<>();
    @XmlElementWrapper(name = "customers")
    @XmlElement(name = "customer")
    private List<Customer> customers = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                ", customers=" + customers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(name, company.name) && Objects.equals(employees, company.employees) && Objects.equals(customers, company.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employees, customers);
    }
}
