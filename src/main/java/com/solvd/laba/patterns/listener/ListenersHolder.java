package com.solvd.laba.patterns.listener;

import com.solvd.laba.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class ListenersHolder {
    private static final List<EmployeeListener> listeners = new ArrayList<>();
    public static void subscribe(EmployeeListener employeeListener) {
        listeners.add(employeeListener);
    }

    public static void unsubscribe(EmployeeListener employeeListener) {
        listeners.remove(employeeListener);
    }

    public static void onNewEmployee(Employee employee) {
        listeners.forEach(employeeListener -> employeeListener.onNewEmployee(employee));
    }

    public static void onEmployeeDismissal(Employee employee) {
        listeners.forEach(employeeListener -> employeeListener.oneEmployeeDismissal(employee));
    }
}
