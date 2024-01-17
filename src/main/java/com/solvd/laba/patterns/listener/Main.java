package com.solvd.laba.patterns.listener;

import com.solvd.laba.domain.Employee;

public class Main {
    public static void main(String[] args) {
        ListenersHolder.subscribe(new AccountingListener());
        ListenersHolder.subscribe(new HRListener());
        ListenersHolder.onNewEmployee(new Employee());
    }
}
