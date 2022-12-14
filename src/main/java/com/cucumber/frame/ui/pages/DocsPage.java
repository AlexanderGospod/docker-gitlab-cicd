package com.cucumber.frame.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.String.format;

public class DocsPage extends BasePage{
    private static final String PAGE_TITLE_PATTERN = "//*[@class= 'page-header']/*[contains(@class, 'title')][contains(text(), '%s')]";
    private static final By MENU_ITEM = By.className("panel-group"); //MENU_ITEM,
    public boolean isPageWithTitleDisplayed(String title){
        return isElementDisplayed(By.xpath(format(PAGE_TITLE_PATTERN, title)));
    }
    public List<WebElement> getMenuItemTitles(){
        return findElements(MENU_ITEM);
    }

}
