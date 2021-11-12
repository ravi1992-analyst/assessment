package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private String temp = "//span[@id='temperature']";
    static String buySunscreenBtn = "//button[normalize-space()='Buy sunscreens']";
    static String buyMoistBtn = "//button[contains(text(),'Buy moisturizers')]";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        System.out.println(driver.getTitle());
        return driver.getTitle();
    }

    public int getTemp(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(temp)));
        String liveTemp = driver.findElement(By.xpath(temp)).getText();
        String[] let = liveTemp.split(" ");
        int tempInInt = Integer.parseInt(let[0]);
        return tempInInt;
    }

    public void clickOnMoist(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buyMoistBtn)));
        driver.findElement(By.xpath(buyMoistBtn)).click();
    }

    public void clickOnSunscrn(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buySunscreenBtn)));
        driver.findElement(By.xpath(buySunscreenBtn)).click();
    }


}
