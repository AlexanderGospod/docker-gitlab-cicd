package com.cucumber.frame.ui.pages;

import com.cucumber.frame.ui.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.String.format;

public class HomePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));
    private static final String CUCUMBER_URL = "https://cucumber.io/";
    private static final String TEXT_PATTERN = "%s[contains(text(), '%s')]";
    private static final String MENU_SECTION = "//*[contains(@class, 'nav-link')][contains(text(), 'Docs')]";
    private static final String CHILD_MENU_ITEM = "//*[contains(@class, 'dropdown-mega')]//*[contains(@class, 'item')]";
    private static final String ALLOW_ALL_COOKIES_BUTTON = "//*[contains(@class, 'dialog-actions')]/*[contains(@class, 'allow-all-btn')]";

    public void openCucumberWebsite() {
        DriverManager.getDriver().get(CUCUMBER_URL);
    }

    public void allowAllCookies() {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(format(ALLOW_ALL_COOKIES_BUTTON))));
            findElement(By.xpath(format(ALLOW_ALL_COOKIES_BUTTON))).click();
    }

    public WebElement menuSection(String linkText) {
        return findElement(By.xpath(format(TEXT_PATTERN, MENU_SECTION, linkText)));
    }

    public WebElement menuChildItem(String linkText) {
        return findElement(By.xpath(format(TEXT_PATTERN, CHILD_MENU_ITEM, linkText)));
    }
}
