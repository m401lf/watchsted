package helperUtilities.window;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class WindowHelper {

     static WebDriver driver;
     //Logger log = LogManager.getLogger(WindowHelper.class);

    public WindowHelper(WebDriver driver) {
        WindowHelper.driver = driver;

    }

    public void checkPageIsReady(int loopCount) throws Exception {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(pageLoadCondition);
    }

    public void switchToParentWindow() {
        System.out.println("switching to parent window...");
        System.out.println("switching to parent window...");
        driver.switchTo().defaultContent();
    }

    public void switchToWindow(int index) {
        Set<String> windows = driver.getWindowHandles();
        int i = 1;
        for (String window : windows) {
            if (i == index) {
                System.out.println("switched to : " + index + " window");
                System.out.println("switched to : " + index + " window");
                driver.switchTo().window(window);
            } else {
                i++;
            }
        }
    }

    public void closeAllTabsAndSwitchToMainWindow() {
        Set<String> windows = driver.getWindowHandles();
        String mainWindow = driver.getWindowHandle();

        for (String window : windows) {
            if (!window.equalsIgnoreCase(mainWindow)) {
                driver.close();
            }
        }
        System.out.println("switched to main window");
        System.out.println("switched to main window");
        driver.switchTo().window(mainWindow);
    }

    public void navigateBack() {
        System.out.println("navigating back");
        System.out.println("navigating back");
        driver.navigate().back();
    }

    public void navigateForward() {
        System.out.println("navigating forward");
        System.out.println("navigating forward");
        driver.navigate().forward();
    }

    public static void navigateToNewTab() throws Exception
    {
        String parent= driver.getWindowHandle();
        Set<String> s1=driver.getWindowHandles();
        Iterator<String> I1= s1.iterator();
        while(I1.hasNext())
        {
            String child_window=I1.next();
            if(!parent.equals(child_window))
            {
                Thread.sleep(500);
                driver.switchTo().window(child_window);

            }
        }
    }

    public static void closeChildWindow() throws Exception
    {
        String parent=driver.getWindowHandle();
        Set<String> s1=driver.getWindowHandles();
        Iterator<String> I1= s1.iterator();
        while(I1.hasNext())
        {
            String child_window=I1.next();
            if(!parent.equals(child_window))
            {
                Thread.sleep(500);
                driver.switchTo().window(child_window).close();

            }
        }
        driver.switchTo().window(parent);
    }
}
