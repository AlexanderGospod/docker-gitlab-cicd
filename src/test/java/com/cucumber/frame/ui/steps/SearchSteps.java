package com.cucumber.frame.ui.steps;

import com.cucumber.frame.ui.driver.DriverManager;
import com.cucumber.frame.ui.pages.SearchPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;


public class SearchSteps {
    private SearchPage searchPage = new SearchPage();

    public void openSearchWindow() {
        searchPage.openSearchWindow();
    }
    @When("the user enters the word {string} in the search field")
    public void enterTheWordInTheSearchField(String searchWord ) {
        openSearchWindow();
        searchPage.enterSearchWord(searchWord);
    }
    @When("the user performs a search")
    public void pressEnterInTheSearchField() {
        searchPage.pressEnterInTheSearchField();
    }

    @When("the user filters search results by the {string} parameter")
    public void filtersSearchResultsByTheParameter(String parameter) {
        searchPage.menuSection(parameter).click();
        searchPage.waitUntilPopUpWindowClosed();
    }

    @When("the user opens the first link")
    public void openTheFirstLink() {
        searchPage.openTheFirstLink();
    }

    @Then("the page title and links are the same")
    public void verifyThePageTitleAndLinks() {
        assertThat(searchPage.isPageWithTitleDisplayed(searchPage.readTitleFirstLink()))
                .overridingErrorMessage("Page with title '%s' is not displayed", searchPage.readTitleFirstLink())
                .isTrue();
    }



}
