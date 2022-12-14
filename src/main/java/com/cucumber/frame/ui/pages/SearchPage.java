package com.cucumber.frame.ui.pages;

import com.cucumber.frame.ui.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static java.lang.String.format;

public class SearchPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(7));
    private static final By SEARCH_ICON = By.cssSelector(".header-search-ico");
    private static final By SEARCH_FIELD = By.xpath("//*[contains(@placeholder, 'Search...')]");
    private static final String TEXT_PATTERN = "%s[contains(text(), '%s')]";
    private static final String MENU_SECTION = "//*[@class='locations-filter']/*[contains(@class, 'filter-item')]//*";
    private static final By TITLE_FIRS_LINK = By.xpath("//*[@class='result-list']/a//*[@class='link-title']");
    private String titleFirstLink;
    private static final String PAGE_TITLE_PATTERN = "//*[contains(@class, 'hero-header')][contains(text(), '%s')]";
    private static final By POP_UP_WINDOW_WITH_SEARCH_RESULTS = By.xpath("//*[@class='app-input-auto']");

    public String readTitleFirstLink() {
        return titleFirstLink;
    }


    public void openSearchWindow (){
         findElement(SEARCH_ICON).click();
    }
    private void sendHumanKeys(By element, String text) {
        Random r = new Random();
        for(int i = 0; i < text.length(); i++) {
            try {
                Thread.sleep((int)(r.nextGaussian() * 15 + 100));
            } catch(InterruptedException e) {}
            String s = new StringBuilder().append(text.charAt(i)).toString();
            findElement(element).sendKeys(s);
        }
    }
    public void enterSearchWord (String searchWord){
        findElement(SEARCH_FIELD).click();
        sendHumanKeys(SEARCH_FIELD, searchWord);
        wait.until(ExpectedConditions.visibilityOfElementLocated(POP_UP_WINDOW_WITH_SEARCH_RESULTS));
    }

    public void pressEnterInTheSearchField (){
        findElement(SEARCH_FIELD).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath
                        ("//*[@class='link-title']"), 1));
    }
    public WebElement menuSection(String linkText){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(format(TEXT_PATTERN, MENU_SECTION, linkText))));
        return findElement(By.xpath(format(TEXT_PATTERN, MENU_SECTION, linkText)));
    }
    public void waitUntilPopUpWindowClosed(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(POP_UP_WINDOW_WITH_SEARCH_RESULTS));
    }
    private String getTitleLink(){
        wait.until(ExpectedConditions.elementToBeClickable(TITLE_FIRS_LINK));
        return titleFirstLink = findElement(TITLE_FIRS_LINK).getText();
    }
    public void openTheFirstLink(){
        getTitleLink();
        wait.until(ExpectedConditions.elementToBeClickable(TITLE_FIRS_LINK));
        findElement(TITLE_FIRS_LINK).click();
    }

    public boolean isPageWithTitleDisplayed(String title){
        return isElementDisplayed(By.xpath(format(PAGE_TITLE_PATTERN, title)));
    }
}
