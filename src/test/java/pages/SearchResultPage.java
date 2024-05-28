package pages;

import base.BasePage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.GlobalVars;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SearchResultPage extends BasePage {
    public WebDriver driver;
    final static Logger log = LogManager.getLogger(SearchResultPage.class);

    public SearchResultPage(WebDriver driver) throws IOException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[href='/search']")
    private WebElement search;

    @FindBy(id = "searchTerm")
    private WebElement searchBox;
    @FindBy(id = "searchBtn")
    private WebElement searchButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[1]/h1")
    private WebElement searchHeadingText;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[1]/div[5]/p[1]")
    private WebElement searchReportStatisticsText;


    @FindBy(xpath = "//h1[normalize-space()='Search Results']")
    private WebElement searchResultsHeadingText;

    @FindBy(css = "a[class='settingDrop'] strong")
    private WebElement primarySchoolsDropDownButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[1]/div[1]/div/ul/li/a")
    private WebElement primarySchoolsDropBoxButtonList;

    @FindBy(css = "a[class='sectionDrop'] strong")
    private WebElement OverallEIFDropDownButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/ul/li/a")
    private WebElement OverallEIFDropDownButtonList;

    @FindBy(css = "a[class='gradeDrop'] strong")
    private WebElement anyDropDownButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[1]/div[3]/div/ul/li/a")
    private WebElement anyDropDownButtonList;

    @FindBy(css = ".schName")
    private List<WebElement> listOfSchoolSearchResultDisplayed;

    @FindBy(xpath = "//*[@id='searchedFor']/p[1]/strong[7]")
    private WebElement numberOfSchoolsDisplayed;

    @FindBy(id = "error")
    private WebElement searchErrorMsg;



    @FindBy(css = "p[id='error'] strong")
    private WebElement noResult;

    @FindBy(id = "loading")
    private WebElement spinner;

    public void clickSearchLink(){
        search.click();

    }

    public void enterSearchBox(String keywordToSearch){
        log.info("Entering search");
        searchBox.sendKeys(keywordToSearch);

    }

    public void clickSearchBtn(){
        searchButton.click();
        new WebDriverWait(driver,Duration.ofSeconds(GlobalVars.EXPLICIT_TIMEOUT)).until(ExpectedConditions.invisibilityOf(spinner));

    }


    public String getSearchResultsHeadingText() {
        return searchResultsHeadingText.getText();
    }

    public boolean assertSearchResultsHeadingTextIsDisplayed() {
        return searchResultsHeadingText.isDisplayed();
    }


    public void clickOnSearchButton() {
        searchButton.click();
        new WebDriverWait(driver,Duration.ofSeconds(GlobalVars.EXPLICIT_TIMEOUT)).until(ExpectedConditions.invisibilityOf(spinner));
    }

    public void clickPrimarySchoolsDropDownButton() {
        primarySchoolsDropDownButton.click();

    }
    public String getPrimarySchoolsDropDownButtonText() {
        return primarySchoolsDropDownButton.getText();

    }

    public WebElement getPrimarySchoolsDropBoxButtonList() {
        return primarySchoolsDropBoxButtonList;
    }

    public void clickOverallEIFDropDownButton() {
        OverallEIFDropDownButton.click();

    }

    public String getOverAllEIFDropDownButtonText() {
        return OverallEIFDropDownButton.getText();
    }

    public WebElement getOverallEIFDropDownButtonList() {
        return OverallEIFDropDownButtonList;

    }

    public void clickAnyDropDownButton() {
        anyDropDownButton.click();
    }

    public String getAnyDropDownButtonText() {
        return anyDropDownButton.getText();
    }

    public WebElement getAnyDropDownButtonList() {
        return anyDropDownButtonList;
    }

    public List<WebElement> getListOfSchoolSearched() {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.EXPLICIT_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElements(listOfSchoolSearchResultDisplayed));
    }

    public long getCountOfListOfSchoolSearched() {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalVars.EXPLICIT_TIMEOUT)).until((s)->listOfSchoolSearchResultDisplayed.size() > 5);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", listOfSchoolSearchResultDisplayed.get(0));
        return listOfSchoolSearchResultDisplayed.size();
    }

    public String getNumberOfSchoolsDisplayed() {
        return numberOfSchoolsDisplayed.getText();
    }

    public String getNoSearchResult() {
        return noResult.getText();

    }

    public String assertSearchNoResult() {
        return noResult.getText();

    }

    public String getSearchErrorMsg() {
        return searchErrorMsg.getText();

    }

    public String getSearchHeadingText() {
        return searchHeadingText.getText();
    }

    public String getSearchReportStatisticsText() {
        return searchReportStatisticsText.getText();
    }

    public boolean assertSearchReportStatisticsTextIsDisplayed() {
        return searchReportStatisticsText.isDisplayed();
    }



}


