package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Checkout {
    private WebDriver driver;
    private String checkHeading = "//h2";
    private String productName = "//td[1]";
    private String payWithCard = "//span[contains(text(),'Pay with Card')]";
    private String checkForm = "//div[@class='bodyView']";
    private String email = "//input[@id='email']";
    private String cardNum = "//input[@id='card_number']";
    private String expiryDate = "//input[@id='cc-exp']";
    private String cvv = "//input[@id='cc-csc']";
    private String zip = "//input[@id='billing-zip']";
    private String payBtn = "//span[@class='iconTick']";

    public Checkout(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isOnCheckPage() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(checkHeading)));
        if (driver.findElement(By.xpath(checkHeading)).isDisplayed()) {
            return true;
        } else {
            return false;
        }

    }

    public String getProductName() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(productName)));
        return driver.findElement(By.xpath(productName)).getText();
    }

    public void clickOnPayLink() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(payWithCard)));
        driver.findElement(By.xpath(payWithCard)).click();
    }

    public boolean checkOutFormDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(checkForm)));
        return driver.findElement(By.xpath(checkForm)).isDisplayed();
    }

    public void enterCardDetail(List list) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.switchTo().frame(0);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(email)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cardNum)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(expiryDate)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cvv)));


        driver.findElement(By.xpath(email)).sendKeys(list.get(0).toString());
        Thread.sleep(2000);


//        driver.findElement(By.xpath(cardNum)).sendKeys(list.get(1).toString());
        slowTyping(list.get(1).toString(),cardNum);

        Thread.sleep(2000);
//        driver.findElement(By.xpath(expiryDate)).sendKeys(list.get(2).toString());
        slowTyping(list.get(2).toString(),expiryDate);
        Thread.sleep(2000);
        driver.findElement(By.xpath(cvv)).sendKeys(list.get(3).toString());
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(zip)));
        driver.findElement(By.xpath(zip)).sendKeys(list.get(4).toString());
    }

    public void clickOnPayBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(payBtn)));
        driver.findElement(By.xpath(payBtn)).click();
    }
    public void getPaymentConfirmation(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//H2[contains(text(),'PAYMENT SUCCESS')]")));
        System.out.println(driver.findElement(By.xpath("//H2[contains(text(),'PAYMENT SUCCESS')]")).getText());
    }


    public void slowTyping(String value, String xpath) {
        for (int i = 0; i < value.length(); i++) {
            driver.findElement(By.xpath(xpath)).sendKeys(value.substring(i, i + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}