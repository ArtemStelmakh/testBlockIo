package readProperties;

import java.io.IOException;

public class ReadConfig {
    public static String readProperties(String propertiesName)  {
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property = System.getProperty(propertiesName);
        System.out.println(property);
        return property;
    }

}
