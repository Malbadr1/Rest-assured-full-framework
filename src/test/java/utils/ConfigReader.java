package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Loads key-value pairs from config/config.properties.
 */
public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream input = new FileInputStream("config/config.properties");
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("❌ Unable to load config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
