package utilities;

public class GlobalVars {

    /**
     * Config Properties file
     **/
    public final static String CONFIG_PROPERTIES_DIRECTORY = "properties\\config.properties";
    public final static String DATAPROVIDER_JSONPATH = System.getProperty("user.dir") + "//src//test//java//testData//data//Register.json";

    public final static String URL_HOMEPAGE = "https://www.watchsted.com";


    //=================================================================//

    public final static int explicitWait = 15;

    public final static int expWait = 5;
    public final static int explicit_Wait = 2;
    public final static int IMPLICIT_WAIT = 2;
    public final static int DEFAULT_EXPLICIT_TIMEOUT = 15;

    public final static int EXPLICIT_TIMEOUT = 30;
    public final static int POLLING_TIMEOUT = 250;
    public final static int PAGELOAD_TIMEOUT = 120;

    public final static int DEFAULT_IMPLICIT_TIMEOUT = 7;

    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final int PAGE_LOAD_TIME = 15;
    public static final int EXPLICIT_WAIT_BASIC_TIME = 30;
    public final static int implicitWait = 10;


    public static String getConfigPropertiesDirectory() {
        return CONFIG_PROPERTIES_DIRECTORY;
    }

    public static String getDataProviderJsonPath() {
        return DATAPROVIDER_JSONPATH;
    }



    public static String getHomePageUrl() {
        return URL_HOMEPAGE;
    }



    public static int getExplicitWait() {
        return explicitWait;
    }

    public static int explicitWait() {
        return expWait;
    }


    public static int getExplicit_Wait() {
        return explicit_Wait;
    }


    public static int getImplicitWait() {
        return implicitWait;
    }

    public static int getPollinTimeout() {
        return POLLING_TIMEOUT;
    }

    public static int getDefaultExplicitTimeout() {
        return DEFAULT_EXPLICIT_TIMEOUT;
    }

    public static int getMaxExplicitTimeout() {
        return EXPLICIT_TIMEOUT;
    }

    public static int getPageLoadTimeout() {
        return PAGELOAD_TIMEOUT;
    }

    public static int getDefaultImplicitTimeout() {
        return DEFAULT_IMPLICIT_TIMEOUT;
    }

}

