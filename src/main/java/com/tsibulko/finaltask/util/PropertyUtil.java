package com.tsibulko.finaltask.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    public static Properties get(String path) throws IOException {
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream(path);
        properties.load(in);
        return properties;
    }
}
