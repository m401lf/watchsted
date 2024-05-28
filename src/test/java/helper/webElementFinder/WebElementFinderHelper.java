package helper.webElementFinder;

import com.google.common.collect.Ordering;
import helper.assertion.VerificationHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class WebElementFinderHelper {

    WebDriver driver;

    public static List<WebElement> findAllChildElementsOfParentElementInActualOrder(WebElement parentWebElement) {
        List<WebElement> childWebElementsInActualOrder = parentWebElement.findElements(By.xpath(".//*"));
        String tagName = childWebElementsInActualOrder.get(childWebElementsInActualOrder.size() - 1).getText();
        return childWebElementsInActualOrder;
    }

    private List<String> getIdListOfExpectedChildElements(List<String> elementIds) {
        List<String> ids = new ArrayList<>();
        for (String elementId : elementIds) {
            ids.add(elementId);
        }
        ids.remove(0);
        return ids;
    }

    public boolean assertListAscendingOrder(List<Long> list) {
        return Ordering.natural().isOrdered(list);

    }

    public boolean assertAnItemFromListIsDisplayed(List<WebElement> elements, String itemFromList) {
        return new VerificationHelper(driver).assertItemDisplayedFromList(elements, itemFromList);
    }

}
