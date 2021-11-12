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

public class BuySunscreens {
    private WebDriver driver;
    static String sunscreenProductList = "//div[@class='container']/div";
    //    static String buySunscreenBtn = "//button[normalize-space()='Buy sunscreens']";
//    static String spf50Price = "//p[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'spf-50')]//following-sibling::p";
    static String spf50Price = "//p[contains(text(),'Price')]";
    static String cartBtn = "//button[@onclick='goToCart()']";
    static String cartBtnText = "//button[@onclick='goToCart()']/span";
    private String cheap;

    public BuySunscreens(WebDriver driver) {
        this.driver = driver;
    }


    public void buySunScrn() {
//        driver.findElement(By.xpath(buySunscreenBtn)).click();
        WebElement elem = null;
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(sunscreenProductList)));

        List<WebElement> sPF50PriceList = driver.findElements(By.xpath(spf50Price));
        Map<WebElement, Integer> mapSPF50 = new HashMap<>();
        for (WebElement ele : sPF50PriceList) {
            String price = ele.getText();
            String priceSplit[] = price.split(" ");
            int priceInInt = Integer.parseInt(priceSplit[priceSplit.length - 1]);
            System.out.println(price);
            mapSPF50.put(ele, priceInInt);
        }
        int min = Collections.min(mapSPF50.values());
        for (Map.Entry<WebElement, Integer> entry : mapSPF50.entrySet()) {
            if (entry.getValue() == min) {
                elem = entry.getKey();
                break;
            }
        }
        cheap = "//" + elem.getTagName() + "[contains(text(),'" + min + "')]//following-sibling::button";
//        driver.findElement(By.xpath(xpath)).click();
        System.out.println("SPF-50 added in the cart");
    }

    public void clickOnAddBtn() {
        driver.findElement(By.xpath(cheap)).click();
    }

    public int getNumOfItems() {
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
