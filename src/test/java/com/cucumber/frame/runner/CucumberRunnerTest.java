package com.cucumber.frame.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = "com.cucumber.frame",
        features = "src/test/resources/"
)

public class CucumberRunnerTest {

}

