package com.solvd.laba.patterns.proxy;

import java.sql.Connection;

public class ConnectionProxy implements ConnectionPreparator {
    private final ConnectionPreparator connectionPreparator;

    public ConnectionProxy() {
        this.connectionPreparator = new ConnectionCreator();
    }

    @Override
    public Connection getConnection() {
        // if there are connection in the pool
        // return connection
        // else
        return connectionPreparator.getConnection();
    }
}
