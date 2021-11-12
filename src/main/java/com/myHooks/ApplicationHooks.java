package com.myHooks;

import com.driverFactory.DriverFactory;
import com.qaUtils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ApplicationHooks {

    private Properties prop;
    private ConfigReader configReader;
    private WebDriver driver;
    private DriverFactory driverFactory;
    @Before(order = 0)
    public void getProperties(){
        configReader = new ConfigReader();
        prop = configReader.initiateProperties();
    }
    @Before(order =1)
    public void launchBrowser(){
        driverFactory = new DriverFactory();
        String browserName = prop.getProperty("browser");
        driverFactory.setUpBrowser(browserName);
        driver = driverFactory.getDriver();
//        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(800,800));
        String url = prop.getProperty("url");
        driver.get(url);
    }


    @After(order = 0)
    public void quitDriver(){
        driver.quit();
    }
    @After(order =1)
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            String scName = scenario.getName().replaceAll(" ","_");
            byte[] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath,"image/png",scName);
            System.out.println("Scenario Failed, Scenario Name: "+ scName);
        }
    }
}
