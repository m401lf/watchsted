package com.watchsted.cucumber.stepDefinitions;

import base.BaseTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.SearchResultPage;
import pages.TopMenuNavigationPage;

import java.io.IOException;

public class steps extends BaseTest {
    TopMenuNavigationPage naviPage;
    HomePage homePage;
    SearchResultPage searchResultPage;


    @Given("I landed on watchsted website")
    public void i_landed_on_watchsted_website() throws IOException {
        naviPage = new TopMenuNavigationPage(driver);
        naviPage = launchApplication();

    }
    @Given("I navigate to Home page")
    public void i_navigate_to_home_page() throws IOException {
        naviPage = launchApplication();
    }

    @Given("I can see page heading {string}")
    public void i_can_see_page_heading(String pageHeader) {
        homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getHomeHeaderText(),pageHeader);
    }

    @And("I click Search link")
    public void i_click_search_link() throws IOException {
        naviPage.clickOnSearchLink();
    }

    @And("I can see page title {string}")
    public void iCanSeePageTitle(String pageTitle) {
        Assert.assertEquals(naviPage.getThisPageTitle(),pageTitle);

    }

    @And("I should see page header {string}")
    public void iShouldSeePageHeader(String pageHeader) throws IOException {
        searchResultPage = new SearchResultPage(driver);
        Assert.assertTrue(searchResultPage.getSearchResultsHeadingText().contains(pageHeader));
    }


    @And("I should be taken to search page title {string}")
    public void iShouldBeTakenToSearchPageTitle(String pageTitle) throws IOException {
        searchResultPage = new SearchResultPage(driver);
        Assert.assertTrue(searchResultPage.getThisPageTitle().contains(pageTitle));
    }

    @And("I should see buttons in search page as follows:")
    public void iShouldSeeLinksInSearchPageAsFollows(DataTable dataTable) {
        Assert.assertTrue(searchResultPage.getPrimarySchoolsDropDownButtonText().contains(dataTable.cell(0,0)));
        Assert.assertTrue(searchResultPage.getOverAllEIFDropDownButtonText().contains(dataTable.cell(1,0)));
        Assert.assertTrue(searchResultPage.getAnyDropDownButtonText().contains(dataTable.cell(2,0)));
    }


    @When("I search with keyword {string}")
    public void iSearchWithKeyword(String keywordToSearch) {
        searchResultPage.enterSearchBox(keywordToSearch);
        searchResultPage.clickOnSearchButton();
    }

    @Then("I can see the search result summary report message as follows:")
    public void i_can_see_the_search_result_summary_report_message_as_follows(DataTable dataTable) {
        Assert.assertTrue(searchResultPage.getSearchReportStatisticsText().contains(dataTable.cell(0,0)));
        Assert.assertTrue(searchResultPage.getSearchReportStatisticsText().contains(dataTable.cell(1,0)));
        Assert.assertTrue(searchResultPage.getSearchReportStatisticsText().contains(dataTable.cell(2,0)));
        Assert.assertTrue(searchResultPage.getSearchReportStatisticsText().contains(dataTable.cell(3,0)));
        Assert.assertTrue(searchResultPage.getSearchReportStatisticsText().contains(dataTable.cell(4,0)));
        Assert.assertTrue(searchResultPage.getSearchReportStatisticsText().contains(dataTable.cell(5,0)));
        Assert.assertTrue(searchResultPage.getSearchReportStatisticsText().contains(dataTable.cell(6,0)));

    }

    @Then("I can see the 10 most relevant reports below {int}")
    public void iCanSeeTheSearchResultCountAs(int resultMsg) throws IOException {
        Assert.assertEquals(resultMsg, searchResultPage.getCountOfListOfSchoolSearched());
        getScreenshot("Search result", driver);

    }

    @When("I search with invalid city name {string}")
    public void iSearchWithInvalidCityName(String searchKeyword) {
        searchResultPage.enterSearchBox(searchKeyword);
        searchResultPage.clickOnSearchButton();
    }

    @Then("I should not see search result {string}")
    public void iShouldNotSeeSearchResult(String searchErrorMessage) throws IOException {
        Assert.assertTrue(searchResultPage.getSearchErrorMsg().contains(searchErrorMessage));

    }

    @And("I close browser")
    public void iCloseBrowser() {
        driver.quit();
    }

    @When("I should see search result for the {string}")
    public void i_should_see_search_result_for_the(String searchKeyword) throws IOException {
        Assert.assertTrue(searchResultPage.getSearchReportStatisticsText().contains(searchKeyword));
        getScreenshot("Search result", driver);

    }
    @Then("I can see the search result count as {int}")
    public void i_can_see_the_search_result_count_as(int resultMsg) throws IOException {
        Assert.assertEquals(resultMsg, searchResultPage.getCountOfListOfSchoolSearched());
        getScreenshot("Search result", driver);
    }

    @Then("I can see the {int} most relevant search result")
    public void i_can_see_the_most_relevant_search_result(int resultMsg) throws IOException {
        Assert.assertEquals(resultMsg, searchResultPage.getCountOfListOfSchoolSearched());
        getScreenshot("Search result", driver);
    }


}
