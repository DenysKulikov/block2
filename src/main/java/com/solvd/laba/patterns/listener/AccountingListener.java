package com.solvd.laba.patterns.listener;

import com.solvd.laba.Main;
import com.solvd.laba.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountingListener implements EmployeeListener {
    private static final Logger LOGGER = LogManager.getLogger(AccountingListener.class);
    @Override
    public void onNewEmployee(Employee employee) {
        LOGGER.info("Collect fin info");
    }

    @Override
    public void oneEmployeeDismissal(Employee employee) {
        LOGGER.info("Pay the employee");
        LOGGER.info("Remove fin info");
    }
}
