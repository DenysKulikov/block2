package com.solvd.laba.patterns.listener;

import com.solvd.laba.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HRListener implements EmployeeListener {
    private static final Logger LOGGER = LogManager.getLogger(AccountingListener.class);
    @Override
    public void onNewEmployee(Employee employee) {
        LOGGER.info("Conduct onboarding");
        LOGGER.info("Provide employee with a laptop");
    }

    @Override
    public void oneEmployeeDismissal(Employee employee) {
        LOGGER.info("Take laptop back");
    }
}
