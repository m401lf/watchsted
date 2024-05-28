package base;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedCondition;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BasePage {

    Logger log = LogManager.getLogger(BasePage.class);
    WebDriver driver;
    protected WebDriverWait wait;
    SearchResultPage searchResultPage;

    TopMenuNavigationPage naviPage;
    HomePage homePage;


    @FindBy(xpath = "/html/body/div[2]/div/aside/div/a")
    List<WebElement> sideWidgetLinks;
    @FindBy(css = ".//*")
    private List<WebElement> allPageElements;

    @FindBy(css = "a.logo")
    private WebElement logo;


    @FindBy(css = "a")
    private WebElement aTag;

    @FindBy(css = "///span[@innertext=' Home']")
    private WebElement homeIcon;

    @FindBy(css = "span.maintext")
    private WebElement pageHeader;

    @FindBy(css = "div[id='content'] h2")
    private WebElement pageHeader_h2;


    @FindBy(xpath = "/html/body/div[1]/div[5]/div[1]/nav/ol/li")
    private List<WebElement> allPageBreadCrumbList;

    @FindBy(xpath = "/html/body/div/div[1]/div[2]/section/ul/li/a")
    private List<WebElement> breadCrumbList;


    @FindBy(xpath = "//*[@id='wishlist-total']/span")
    private WebElement wishlist;

    @FindBy(css = "a[id='wishlist-total'] i[class='fa fa-heart']")
    private WebElement wishlistIcon;

    @FindBy(css = "ul:nth-of-type(2)  h2")
    private WebElement productPrice;
    @FindBy(css = "a:nth-of-type(1) > strong")
    private WebElement viewCartBtn;
    @FindBy(css = "a:nth-of-type(2) > strong")
    private WebElement checkoutBtn;
    @FindBy(css = "td[class='text-left'] a")
    private WebElement productDisplayed;
    @FindBy(css = "td[class='text-left'] small")
    private WebElement deliverDate;
    @FindBy(css = ".dropdown-menu.pull-right")
    private WebElement priceSummaryTable;
    @FindBy(css = ".fa.fa-times")
    private WebElement removeButton;
    



 public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void assertPageBreadCrumbDisplayed(){
        List<WebElement> pageList = allPageBreadCrumbList;
        for(WebElement pageBreadCrumb: allPageBreadCrumbList){
            if(pageBreadCrumb.isDisplayed()){
            }
        }
        System.out.println(pageList);
    }

    public boolean assertPageBreadCrumbMenuDisplayedContains(String MenuName) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        return breadCrumbList.stream().parallel().anyMatch(s -> s.getText().contains(MenuName));
    }

    public void checkPageIsReady(int loopCount) throws Exception {
        ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(pageLoadCondition);
    }

    public static String randomEmailAddress() {
        return (RandomStringUtils.randomNumeric(8)) + "@gmail.com";

    }

    public static boolean selectByVisibleText(String visibleText, WebElement ele) {
        boolean flag = false;
        try {
            Select s = new Select(ele);
            s.selectByVisibleText(visibleText);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Option selected by VisibleText ::" + "<" + visibleText + ">");
            } else {
                System.out.println("Option not selected by VisibleText");
            }
        }
    }

    public WebElement getLogo() {
        return logo;
    }

    public boolean assertListOfDisplayedTextInItemCartIsDisplayed() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        return viewCartBtn.isDisplayed() && checkoutBtn.isDisplayed() && productDisplayed.isDisplayed() && priceSummaryTable.isDisplayed() && removeButton.isDisplayed();
    }

    public List<WebElement> assertPageBreadCrumbDisplayed(int num) {
        List<WebElement> pageList = allPageBreadCrumbList;
        for (WebElement pageBreadCrumb : allPageBreadCrumbList) {
            if (pageBreadCrumb.isDisplayed()) {
                return pageList;
            }
        }
        System.out.println(pageList);
        return pageList;
    }

    public String generateRandomEmail() {
        return (RandomStringUtils.randomNumeric(10)) + "@gmail.com";

    }

    public List<WebElement> getAllElements() {
        return allPageElements;

    }

    public void clickLinkByText(String linkName) throws IOException {
        waitElementToAppearFor(2);
        driver.findElement(By.xpath("//*[contains(text(),'" + linkName + "')]")).click();
        waitElementToAppearFor(1);
        System.out.println("Successfully clicked on the link");
    }

    public List<String> assertHeaderAndLabelsTxt(String headerOrText) {
        System.out.println(headerOrText);
        return allPageElements.stream().map(WebElement::getText).filter(text -> text.equalsIgnoreCase(headerOrText)).collect(Collectors.toList());
    }

    public void waitAndSendKeys(WebElement element, String keysToSend) {
        element.clear();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(keysToSend);
    }

    public void waitAndClick(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        System.out.println("Clicked on :: " + findBy.getText());
        System.out.println("Clicked on :: " + findBy.getText());
        wait.until(ExpectedConditions.elementToBeClickable(findBy)).click();
    }

    public void waitForWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(findBy));

    }

    public void waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        log.info("Waited for element visibility :: " + element.getText());

    }

    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
        log.info("Waited for element visibility :: " + findBy);
    }

    public void waitElementToAppearFor(int timeout) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(timeout));

    }

    public void assertTextShouldNotBeDisplayedInHeadingText(String headingText) {
        WebElement newHeading = driver.findElement(By.id("heading"));
        Assert.assertNotEquals(headingText, newHeading.getText());
    }

    public void waitForAlertAndValidateText(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.alertIsPresent());
        String alert_Message_Text = driver.switchTo().alert().getText();
        Assert.assertEquals(alert_Message_Text, text);
    }

    public void clickMatchingElementByText(List<WebElement> elements, String text) {
        WebElement element = elements.stream().parallel().filter(s -> s.getText().equalsIgnoreCase(text)).findFirst().orElseThrow(() -> new RuntimeException("Element with text" + text + "not present"));
        element.click();

    }

    public void clickAMatchingElementByText(List<WebElement> elements, String ItemName) {
        WebElement element = elements.stream().parallel().filter(s -> s.getText().equalsIgnoreCase(ItemName)).findFirst().orElseThrow(() -> new RuntimeException("Element with text" + ItemName + "not present"));
        element.click();

    }

    public void sendKeysToWebElement(WebElement element, String textToSend) {
        element.clear();
        element.sendKeys(textToSend);
        log.info("sent data to the element :: " + textToSend);
    }


    public void sendKeys(By by, String textToType) {
        System.out.println("Entered text :: " + textToType);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(textToType);
    }

    public void sendKeys(WebElement element, String textToType) {
        element.clear();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToType);
        log.info("Keys successfully sent to field :: " + textToType);
    }

    public void waitForElementToBeClickableAndClick(WebElement element) {
        System.out.println("Waited and Successfully clicked on the element :: " + element.getText());
        waitForWebElementAndClick(element);

    }

    public void waitForElementToBeClickable(WebElement element) {
        System.out.println("Waiting for element to be visible");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        System.out.println("Element now visible Clickable");
    }

    public void waitForWebElementAndClick(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void waitForWebElementAndClick(WebElement element) {
        log.info("Waiting for element to be visible");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        log.info("Successfully clicked on the element");

    }

    public List<String> getAccountsSectionsList(List<WebElement> products) {
        List<String> accountsList = new ArrayList<>();
        List<WebElement> accountsHeaderList = driver.findElements((By) products);
        for (WebElement e : accountsHeaderList) {
            String text = e.getText();
            System.out.println(text);
            accountsList.add(text);
        }
        return accountsList;
    }

    public Iterator<String> getAllWindows() {
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> itr = windows.iterator();
        return itr;
    }

    public void getUrl(String url) {
        System.out.println("navigating to :-" + url);
        driver.get(url);
    }

    public void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", ele);

    }

    public boolean JSClick(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", ele);

            flag = true;

        } catch (Exception e) {
            throw e;

        } finally {
            if (flag) {
                System.out.println("Click Action is performed: " + ele.getText());
            } else if (!flag) {
                System.out.println("Click Action is not performed: " + ele.getText());
            }
        }
        return flag;
    }


    public ArrayList<Integer> getPriceMassagedData(Iterator<WebElement> itr) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        while (itr.hasNext()) {
            String p = itr.next().getText();
            if (p.contains("$")) {
                String actualData = p.substring(1);
                //System.out.println(actualData);
                double p1 = Double.parseDouble(actualData);
                int productPrice = (int) (p1);
                array.add(productPrice);
            }
        }
        return array;
    }

    public void waitForElementInvisible(WebElement element, int timer) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void clickAnItemFromListByText(List<WebElement> elements, String itemText) {
        elements.
                stream()
                .parallel()
                .filter(e -> e.getText().equalsIgnoreCase(itemText))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void clickAnElementMatchingText(List<WebElement> elements, String itemText) {
        WebElement element = elements
                .stream()
                .parallel()
                .filter(e -> e.getText().equalsIgnoreCase(itemText))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Element with text" + itemText + " not present"));
        element.click();
    }

    public boolean assertElementIsDisplayedAndEnabled(WebElement element) {
        return element.isDisplayed() && element.isEnabled();
    }

    public void assertOnSubHeadingText(String subHeadingText) {
        WebElement subHeading = driver.findElement(By.id("sub_heading"));
        Assert.assertEquals(subHeadingText, subHeading.getText());
    }

    public List<WebElement> breadCrumbList() {
        return allPageBreadCrumbList;

    }

    public boolean assertCurrentPageBreadCrumbByText(String MenuName) {
        for(int i=0;i<allPageBreadCrumbList.size();i++) {
            if(allPageBreadCrumbList.get(i).getText().contains(MenuName)) {
                return true;
            }
        }
        return true;
    }

    public boolean assertCurrentPageBreadCrumbByTextName(String menuName) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        return allPageBreadCrumbList.stream().map(s->s.getText()).anyMatch(s->s.contains(menuName));
    }

    public boolean assertItemDisplayedFromList(List<WebElement> listElements, String item) {
        return listElements.stream().parallel().anyMatch(s -> s.getText().equalsIgnoreCase(item));
    }


    public String getThisPageUrl() {
        return driver.getCurrentUrl();

    }

    public String getThisPageTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.titleIs(driver.getTitle()));
        return driver.getTitle();

    }

    public void assertWebElementNotDisplayedInCurrentPage(By locator) {
        try {
            driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            Assert.assertTrue(e.getMessage().contains("no such element: Unable to locate element:"));
        }
    }

    public void clickOnBrowserForwardButton() {
        driver.navigate().forward();

    }
    public void clickOnBrowserBackButton() {
        driver.navigate().back();

    }

    public void navigateTo(String url) {
        driver.navigate().to(url);

    }


    public void mouseOver(String data) {
        System.out.println("doing mouse over on :" + data);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'" + data + "')]"))).build().perform();
    }

    public void mouseOverElement(WebDriver driver, WebElement element) {
        boolean flag = false;
        try {
            new Actions(driver).moveToElement(element).build().perform();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (flag) {
                System.out.println(" MouserOver Action is performed on: " + element.getText());
            } else {
                System.out.println("MouseOver action is not performed on");
            }
        }
    }


    // Wait for element & click on element
    public void clickOnElement(WebElement element, long durationInSeconds) {
        WebElement webElement = waitForElement(element, durationInSeconds);
        webElement.click();
    }

    public void sendKeysElement(WebElement element, String textToType) {
        element.clear();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToType);
        System.out.println("Keys successfully sent to field :: " + textToType);
    }

    // Type text into element
    public void typeTextIntoElement(WebElement element, String textToBeTyped, long durationInSeconds) {
        WebElement webElement = waitForElement(element, durationInSeconds);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(textToBeTyped);
    }

    // Select option in dropdown
    public void selectOptionInDropdown(WebElement element, String dropDownOption, long durationInSeconds) {
        WebElement webElement = waitForElement(element, durationInSeconds);
        Select select = new Select(webElement);
        select.selectByVisibleText(dropDownOption);
    }

    // Alert accept & reject
    public void acceptAlert(long durationInSeconds) {
        Alert alert = waitForAlert(durationInSeconds);
        alert.accept();
    }

    public void dismissAlert(long durationInSeconds) {
        Alert alert = waitForAlert(durationInSeconds);
        alert.dismiss();
    }

    // Mouse hover & click
    public void mouseHoverAndClick(WebElement element, long durationInSeconds) {
        WebElement webElement = waitForVisibilityOfElement(element, durationInSeconds);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).click().build().perform();
    }

    public WebElement waitForVisibilityOfElement(WebElement element, long durationInSeconds) {
        WebElement webElement = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
            webElement = wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return webElement;
    }

    public WebElement waitForElement(WebElement element, long durationInSeconds) {
        WebElement webElement = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
            webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return webElement;
    }

    public Alert waitForAlert(long durationInSeconds) {
        Alert alert = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
            alert = wait.until(ExpectedConditions.alertIsPresent());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return alert;
    }

    // JavaScript Click
    public void javaScriptClick(WebElement element, long durationInSeconds) {
        WebElement webElement = waitForVisibilityOfElement(element, durationInSeconds);
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("arguments[0].click();", webElement);
    }

    // JavaScript Type
    public void javaScriptType(WebElement element, long durationInSeconds, String textToBeTyped) {
        WebElement webElement = waitForVisibilityOfElement(element, durationInSeconds);
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("arguments[0].value='" + textToBeTyped + "'", webElement);
    }

    // Get text from element
    public String getTextFromElement(WebElement element, long durationInSeconds) {
        WebElement webElement = waitForVisibilityOfElement(element, durationInSeconds);
        return webElement.getText();
    }

    // Display status of element
    public boolean displayStatusOfElement(WebElement element, long durationInSeconds) {
        try {
            WebElement webElement = waitForVisibilityOfElement(element, durationInSeconds);
            return webElement.isDisplayed();
        } catch (Throwable e) {
            return false;
        }

    }

    //===============Click Methods===================//

    public void submit(WebElement element) {
        element.submit();

    }

    public void clickUsingActions(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).click().build().perform();

    }

    public void JSClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

    }

    public void clickUsingMoseAction(WebElement element) {
        element.sendKeys(Keys.RETURN);

    }

    public void WaitForElementAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void screenshotAnElement(WebElement element) throws IOException {
        File srcScreenShot = element.getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcScreenShot, new File(System.getProperty("user.dir") + "//screenShots/screenShot.png"));
    }

    public void screenshotFullPage() throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "//screenShots/file1.png"));
    }

    public void getRectHeightAndWidthOfAnElement(WebElement element) throws IOException {
        Point p = element.getLocation();
        System.out.println(p.getX());
        System.out.println(p.getY());
        Rectangle r = element.getRect();
        System.out.println(r.getX());
        System.out.println(r.getY());
    }


    public void waitForElement(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.ignoring(NoSuchElementException.class);
        //wait.ignoring(ElementNotVisibleException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(ElementNotFoundException.class);
        wait.pollingEvery(Duration.ofSeconds(5));
        wait.until(elementLocated(element));
    }

    private Function<WebDriver, Boolean> elementLocated(final WebElement element) {
        return new Function<WebDriver, Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return element.isDisplayed();

            }
        };
    }

    public boolean checkForTitle(String title) {
        System.out.println(title);
        if (title == null || title.isEmpty())
            throw new IllegalArgumentException(title);
        return driver.getTitle().trim().contains(title);
    }


    public void clickLogoImage() {
        logo.click();

    }

    public WebElement getPageBreadcrumbMenuList(String breadCrumbMenuName) {
        for (int i = 0; i < allPageBreadCrumbList.size(); i++) {
           if(allPageBreadCrumbList.get(i).getText().contains(breadCrumbMenuName)){
               return allPageBreadCrumbList.get(i);

           }
        }


        return null;
    }
}
