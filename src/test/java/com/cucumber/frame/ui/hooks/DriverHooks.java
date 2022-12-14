package com.cucumber.frame.ui.hooks;

import com.cucumber.frame.ui.driver.DriverManager;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import static com.cucumber.frame.ui.driver.DriverManager.getDriver;

public class DriverHooks {
    @Before("@UI")
    public void setupDriver(Scenario scenario) {
        DriverManager.setUpDriver();
    }

    @After("@UI")
    public void saveScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.addAttachment("Any Name", new ByteArrayInputStream(((TakesScreenshot) getDriver())
                    .getScreenshotAs(OutputType.BYTES)));
        }
            DriverManager.quitDriver();
    }
}
