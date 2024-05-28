package helper.browserConfiguration.config;


public interface ConfigReader {

    int getImplicitWait();

    int getExplicitWait();

    int getPageLoadTime();

    String getUrl();

    String getUserName();

    String getPassword();

}
