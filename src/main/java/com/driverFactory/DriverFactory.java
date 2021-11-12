package com.driverFactory;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public static WebDriver driver;

    public void setUpBrowser(String broswerName) {
        if (broswerName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            //Extension can be added with chrome options here
            ChromeOptions options = new ChromeOptions();
//            String CRXfilePath = "C://chormeExtion.crx";
//            options.addExtensions(new File(CRXfilePath));
            driver = new ChromeDriver(options);
        } else if (broswerName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            System.out.println("Please provide the correct browser");
        } else if (broswerName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else{
            System.out.println("<<<<<<<<Please check the browser name in cofig.properties file>>>>>>>");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

}
