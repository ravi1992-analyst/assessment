package com.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/resources/FeatureFiles/test123.feature"},
        glue = {"src/main/java/com/stepDefinition", "src/main/java/com/myHooks"},
        plugin = {"pretty","html:src/cucumber.html"},
        publish = true
)
public class TestRunner {

}
