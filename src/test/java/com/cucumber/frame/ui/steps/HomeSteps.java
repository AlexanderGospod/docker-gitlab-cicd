package com.cucumber.frame.ui.steps;

import com.cucumber.frame.ui.pages.HomePage;
import io.cucumber.java.en.When;

public class HomeSteps {
    private HomePage homePage = new HomePage();

    @When("the user clicks on the {string} button")
    public void allowAllCookies(String item) {
        homePage.allowAllCookies();
    }
}
