package helper.browserConfiguration.config;

import helper.resource.ResourceHelper;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class PropertyReader implements ConfigReader {

    public static Properties OR;
    private static FileInputStream file;

    public PropertyReader() {
        try {
            String filePath = ResourceHelper.getResourcePath("src/main/resources/com.automationteststore.configfile/config.properties");
            file = new FileInputStream(new File(filePath));
            OR = new Properties();
            OR.load(file);

            String filePath1 = ResourceHelper.getResourcePath("src/main/resources/com.automationteststore.configfile/config1.properties");
            file = new FileInputStream(new File(filePath1));
            OR = new Properties();
            OR.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getImplicitWait() {
        return Integer.parseInt(OR.getProperty("implicitwait"));
    }

    public int getExplicitWait() {
        return Integer.parseInt(OR.getProperty("explicitwait"));
    }

    public int getPageLoadTime() {
        return Integer.parseInt(OR.getProperty("pageloadtime"));
    }

    public String getUrl() {
        if (System.getProperty("url") != null) {
            return System.getProperty("url");
        }
        return OR.getProperty("applicationUrl");
    }

    public String getUserName() {
        if (System.getProperty("userName") != null) {
            return System.getProperty("userName");
        }
        return OR.getProperty("userName");
    }

    public String getPassword() {
        if (System.getProperty("password") != null) {
            return System.getProperty("password");
        }
        return OR.getProperty("password");
    }

}
