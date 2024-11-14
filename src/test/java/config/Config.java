package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static int getExplicitTimeout() {
        return Integer.parseInt(get("explicit.timeout"));
    }
}
