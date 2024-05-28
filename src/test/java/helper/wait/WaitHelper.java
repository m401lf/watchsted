package helper.wait;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WaitHelper {

    WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        //System.out.println("WaitHelper object created..");
    }

    private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(NoSuchFrameException.class);
        return wait;
    }

    public void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds,
                                                     int pollingEveryInMiliSec) {
        System.out.println("Waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        wait.until(ExpectedConditions.visibilityOf(element));
        System.out.println("Element is visible now");
    }

    public void WaitForElementClickable(WebElement element, int timeOutInSeconds) {
        System.out.println("Waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(element));
        System.out.println("Element is clickable now");
    }

    public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
        System.out.println("Waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
        System.out.println("Element is invisibile now");
        return status;
    }

    public void waitForFrameToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
        System.out.println("Waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        System.out.println("Frame is available and switched");
    }

    private Wait<WebDriver> getfluentWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
        Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);
        return fWait;
    }

    public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec) {
        Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, pollingEveryInMiliSec);
        fwait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public void pageLoadTime() {
        System.out.println("Waiting for page to load for ...");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        System.out.println("BasePage is loaded");
    }

    public void waitForElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(element));
        System.out.println("Waited and element is now visible :: " + element.getText());
    }

    public void setImplicitWait(int implicitWait, TimeUnit seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void setPageLoadTimeout(int pageLoadTimeOut) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeOut));
    }
}
