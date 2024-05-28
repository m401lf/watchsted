package helperUtilities.assertion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class VerificationHelper {

    //Logger log = LogManager.getLogger(VerificationHelper.class);
    WebDriver driver;

    public VerificationHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            System.out.println("Element is Displayed: " + element);
            return true;
        } catch (Exception e) {
            System.out.println("Element is not Displayed..");
            return false;
        }
    }

    public boolean assertItemDisplayedFromList(List<WebElement> listElements, String item) {
        return listElements.stream().parallel().anyMatch(s -> s.getText().equalsIgnoreCase(item));
    }

    public boolean isSelected(WebElement element) {
        try {
            element.isSelected();
            System.out.println("The element is Selected :: " + "<<" + element + ">>");
            return true;
        } catch (Exception e) {
            System.out.println("Element is not Selected.... ");
            //log.error("Element is not Selected: ", e.getCause());
            return false;
        }
    }

    public boolean assertElementEnabledAndDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            element.isEnabled();
            System.out.println("Element is enabled :: " + element.getText());
            return true;
        } catch (Exception e) {
            //log.error("Element is not enabled..", e.getCause());
            //log.error("Element is not Displayed..", e.getCause());
            return false;
        }
    }

    public boolean isEnabled(WebElement element) {
        try {
            element.isEnabled();
            //System.out.println("Element is enabled :: " + element.getText());
            return true;
        } catch (Exception e) {
            //log.error("Element is not enabled..", e.getCause());
            return false;
        }
    }

    public boolean isNotDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            //System.out.println("element is present.." + element.getText());
            return false;
        } catch (Exception e) {
            //log.error("Element is not present..");
            return true;
        }
    }

    public String readValueFromElement(WebElement element) {
        if (null == element) {
            System.out.println("WebElement is null..");
            return null;
        }
        boolean status = isDisplayed(element);
        if (status) {
            System.out.println("Element text is .." + element.getText());
            return element.getText();
        } else {
            return null;
        }
    }

    public String getText(WebElement element) {
        if (null == element) {
            System.out.println("WebElement is null..");
            return null;
        }
        boolean status = isDisplayed(element);
        if (status) {
            System.out.println("Element text is displayed:: " + element);
            return element.getText().trim();
        } else {
            return null;
        }
    }

    public String getText(List<WebElement> elements) {
        if (null == elements) {
            System.out.println("WebElement is null..");
            return null;
        }
        boolean status = isDisplayed((WebElement) elements);
        if (status) {
            System.out.println("Element text is displayed:: " + elements);
            return ((WebElement) elements).getText().trim();
        } else {
            return null;
        }
    }

    public  String getTitle() {
        //System.out.println("BasePage title is: " + driver.getTitle());
        return driver.getTitle();
    }

    public String getCurrentPageTitle() {
        //System.out.println("BasePage title is: " + driver.getTitle());
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        //System.out.println("BasePage Url is: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    public String getCurrentPageUrl() {
        //System.out.println("BasePage Url is: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

}
