package com.epam.lab.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    private static final String PROPERTIES_FILE_PATH = "src/main/resources/file.properties";

    public static Properties readProperties() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(PROPERTIES_FILE_PATH);
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }
}