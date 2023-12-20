package com.solvd.laba.persistence;

import java.sql.SQLException;

public class CreateConnectionExeption extends Throwable {
    public CreateConnectionExeption(String string, SQLException e) {
        super(string, e);
    }
}
