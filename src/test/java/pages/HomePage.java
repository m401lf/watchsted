package pages;

import base.BasePage;
import io.cucumber.java.ja.但し;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage {
    final static Logger log = LogManager.getLogger(HomePage.class);
    WebDriver driver;
    HomePage homePage;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mapTitle")
    private WebElement homeHeader;

    public String getHomeHeaderText() {
    waitFor(homeHeader);
        log.info("Getting home header text");
    return homeHeader.getText();

    }




}
