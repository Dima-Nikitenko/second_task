package framework;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

public class PropertiesHandler {

    Properties property = new Properties();

    private void setupFileReader() {
        try (FileInputStream data = new FileInputStream("src/test/resources/config.properties")) {
            property.load(data);
        } catch (IOException e) {
            System.out.println("Config.properties file not found or cannot be read.");
        }
    }

    public String getProperty(String key) {
        setupFileReader();
        if(key!= null) return property.getProperty(key);
        else throw new RuntimeException("Browser name is not specified in the config.properties file.");
    }
}
