package com.solvd.laba.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String DRIVER;
    public static String URL;
    public static String USERNAME;
    public static String PASSWORD;
    public static int POOL_SIZE;

    static {
        try(FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);

            DRIVER = properties.getProperty("driver");
            URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
            POOL_SIZE = Integer.parseInt(properties.getProperty("poolSize"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
