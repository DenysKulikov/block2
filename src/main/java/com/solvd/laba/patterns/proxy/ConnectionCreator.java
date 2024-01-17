package com.solvd.laba.patterns.proxy;

import com.solvd.laba.persistence.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator implements ConnectionPreparator {
    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
