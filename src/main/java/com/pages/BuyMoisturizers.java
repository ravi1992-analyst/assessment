package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyMoisturizers {
    private WebDriver driver;
    private String temp = "//span[@id='temperature']";
//    public int tempInINT = getTemp();
    static String buyMoistBtn = "//button[contains(text(),'Buy moisturizers')]";
    static String moistProductList = "//div[@class='container']/div";
//    static String almondPrice = "//p[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'almond')]//following-sibling::p";
    static String moistPriceList = "//p[contains(text(),'Price')]";
    static String cartBtn = "//button[@onclick='goToCart()']";
    static String cartBtnText = "//button[@onclick='goToCart()']/span";

    static String cheap ;


    private WebElement elem = null;






    public BuyMoisturizers(WebDriver driver){
        this.driver=driver;
    }


    public void buyMoist(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
//        driver.findElement(By.xpath(buyMoistBtn)).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(moistProductList)));

        List<WebElement> priceList = driver.findElements(By.xpath(moistPriceList));
        Map<WebElement, Integer> map = new HashMap<>();
        for (WebElement ele : priceList) {
            String price = ele.getText();
            String priceSplit[] = price.split(" ");
            int priceInInt = Integer.parseInt(priceSplit[priceSplit.length - 1]);
            System.out.println(price);
            map.put(ele, priceInInt);
        }

        int min = Collections.min(map.values());
        for (Map.Entry<WebElement, Integer> entry : map.entrySet()) {
            if (entry.getValue() == min) {
                elem = entry.getKey();
                break;
            }
        }
        cheap  = "//" + elem.getTagName() + "[contains(text(),'" + min + "')]//following-sibling::button";

        System.out.println("Cheapest Moist found");

    }

    public int getTemp(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(temp)));
        String liveTemp = driver.findElement(By.xpath(temp)).getText();
        String[] let = liveTemp.split(" ");
        int tempInInt = Integer.parseInt(let[0]);
        return tempInInt;
    }

    public String getTitle(){
        return driver.getTitle();
    }
    public void clickOnAddBtn(){
        driver.findElement(By.xpath(cheap)).click();
    }

    public int getNumOfItems(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cartBtnText)));
        String items = driver.findElement(By.xpath(cartBtnText)).getText();
        String[] let = items.split(" ");
        int _item = Integer.parseInt(let[0]);
        return _item;
    }

    public void clickCartButton(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cartBtn)));
        driver.findElement(By.xpath(cartBtn)).click();
    }
}
