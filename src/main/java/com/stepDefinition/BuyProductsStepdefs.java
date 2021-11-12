package com.stepDefinition;

import com.driverFactory.DriverFactory;
import com.pages.BuyMoisturizers;
import com.pages.BuySunscreens;
import com.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class BuyProductsStepdefs {
    private BuyMoisturizers buyMoisturizers = new BuyMoisturizers(DriverFactory.getDriver());
    private BuySunscreens buySunscreens = new BuySunscreens(DriverFactory.getDriver());
    private String flag = "";

    @When("User clicks on add cheapest the product")
    public void user_clicks_on_add_cheapest_the_product() {
        String buy = buyMoisturizers.getTitle();
        if (buy.contains("moisturizer")){
            buyMoisturizers.buyMoist();
            buyMoisturizers.clickOnAddBtn();
            flag = "moist";
        }else{
            buySunscreens.buySunScrn();
            buySunscreens.clickOnAddBtn();
            flag = "sunscreen";
        }
    }

    @Then("Product should add to the cart")
    public void product_should_add_to_the_cart() {
        if (flag.equalsIgnoreCase("moist")){
            Assert.assertEquals(buyMoisturizers.getNumOfItems(),1);
        }else{
            Assert.assertEquals(buySunscreens.getNumOfItems(),1);
        }
    }
    @When("User click on the cart button")
    public void user_click_on_the_cart_button() {
        if (flag.equalsIgnoreCase("moist")){
            buyMoisturizers.clickCartButton();
        }else{
            buySunscreens.clickCartButton();
        }
    }
}
