package Statics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Globals {
    private static final Properties properties = new Properties();

    static {
        File propertiesFile = new File("configuration.properties");
        try {
            FileInputStream inputStream = new FileInputStream(propertiesFile);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final int WIDTH = Integer.parseInt(properties.getProperty("screen_width"));

    public static final int HEIGHT = Integer.parseInt(properties.getProperty("screen_height"));

    public static final double ERROR = Double.parseDouble(properties.getProperty("error"));

    public static final int DEPTH = Integer.parseInt(properties.getProperty("render_depth"));
}
