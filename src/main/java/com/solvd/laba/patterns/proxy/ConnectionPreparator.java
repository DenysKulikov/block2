package com.solvd.laba.patterns.proxy;

import java.sql.Connection;

public interface ConnectionPreparator {
    Connection getConnection();
}
