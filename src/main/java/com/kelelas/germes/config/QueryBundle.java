package com.kelelas.germes.config;

import java.util.ResourceBundle;

public class QueryBundle {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("query");
    private QueryBundle() {
    }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
