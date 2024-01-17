package com.solvd.laba.patterns.listener;

import com.solvd.laba.domain.Employee;

public interface EmployeeListener {
    void onNewEmployee(Employee employee);
    void oneEmployeeDismissal(Employee employee);
}
