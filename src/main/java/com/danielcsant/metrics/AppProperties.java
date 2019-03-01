package com.danielcsant.metrics;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private static Properties prop;

    private static Properties getPropertiesInstance() throws IOException {

        if (prop == null){
            prop = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("config.properties");
            prop.load(stream);
        }

        return prop;
    }

    public static String getProperty(String prop){
        String value = "";

        try {
            value = getPropertiesInstance().getProperty(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

}
