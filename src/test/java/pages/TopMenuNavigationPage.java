package pages;

import base.BasePage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class TopMenuNavigationPage extends BasePage {

    public WebDriver driver;

    final static Logger log = LogManager.getLogger(TopMenuNavigationPage.class);

    public TopMenuNavigationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div[1]/div[1]/ul/li/a")
    private List<WebElement> topMenuList;


    @FindBy(css = "a[href='/search']")
    private WebElement searchLink;


    public void navigateToIndexPage() {
        driver.get("https://www.watchsted.com");

    }


    public List<String> getTopMenuList() {
        return topMenuList.stream().parallel().filter(link -> link.isDisplayed() && link.isEnabled()).map(WebElement::getText).toList();

    }

    public void clickOnAnItemFromTopMenuListByText(String item) {
        for(int i = 0; i>topMenuList.size(); i++){
            if(topMenuList.get(i).getText().equalsIgnoreCase(item)){
                topMenuList.get(i).click();
                break;
            }
        }
    }

    public SearchResultPage clickOnSearchLink() throws IOException {
        searchLink.click();
        return new SearchResultPage(driver);
    }

    public boolean getSearchLink() {
        return searchLink.isDisplayed();

    }

}