package helper.javaScript;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {

     //Logger log = LogManager.getLogger(JavaScriptHelper.class);// for Logger;
    WebDriver driver;

    public JavaScriptHelper(WebDriver driver) {
        this.driver = driver;
        //System.out.println("JavaScriptHelper has been initialised");
    }

    public Object executeScript(String script) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        return exe.executeScript(script);
    }

    public Object executeScript(String script, Object... args) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        return exe.executeScript(script, args);
    }

    public void scrollToElement(WebElement element) {
        System.out.println("scroll to WebElement :: " + element.getText());
        System.out.println("scroll to <" + element.getText() + "> WebElement");
        executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x,
                element.getLocation().y);
    }

    public void scrollToElementAndClick(WebElement element) {
        scrollToElement(element);
        element.click();
        System.out.println("element is clicked: " + element.getText());
        System.out.println("element is clicked: " + element.getText());
    }

    public void scrollIntoView(WebElement element) {
        System.out.println("scroll till web element :: " + element.getText());
        System.out.println("scroll till web element :: " + element.getText());
        executeScript("arguments[0].scrollIntoView()", element);
    }

    public void scrollIntoViewAndClick(WebElement element) {
        scrollIntoView(element);
        System.out.println("element is clicked: " + element.getText());
        System.out.println("element is clicked: " + element.getText());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

    }

    public void scrollDownVertically() {
        System.out.println("scrolling down vertically...");
        System.out.println("scrolling down vertically...");
        executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public void scrollUpVertically() {
        System.out.println("scrolling up vertically...");
        System.out.println("scrolling up vertically...");
        executeScript("window.scrollTo(0,-document.body.scrollHeight)");
    }

    public void scrollDownByPixel(int pixel) {
        executeScript("window.scrollBY(0," + pixel + ")");
    }

    public void scrollUpByPixel(int pixel) {
        executeScript("window.scrollBY(0,-" + pixel + ")");
    }

    public void zoomInBy100Percentage() {
        executeScript("document.body.style.zoom='100%'");
    }

    public void zoomInBy60Percentage() {
        executeScript("document.body.style.zoom='40%'");
    }

    public void jsClickElement(WebElement element) {
        System.out.println("Click on the element :: " + element.getText());
        executeScript("arguments[0].click();", element);
    }


}
