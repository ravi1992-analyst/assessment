package com.stepDefinition;

import com.driverFactory.DriverFactory;
import com.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class HomePageStepdefs {
    private HomePage homePage = new HomePage(DriverFactory.getDriver());
    String title;
    @Given("User is on home page")
    public void userIsOnHomePage() {
        title = homePage.getTitle();
    }

    @Given("User verifies the title {string} of the page")
    public void user_verifies_the_title_of_the_page(String title) {
     Assert.assertEquals(this.title,title);
    }

    @When("User clicks on Buy Moisturizers button OR Buy Sunscreens button based on current temperature")
    public void user_clicks_on_buy_moisturizers_button_or_buy_sunscreens_button_based_on_current_temperature() {
        if (homePage.getTemp()<19){
            homePage.clickOnMoist();
            System.out.println("buy moist clicked");
        } else {
            homePage.clickOnSunscrn();
            System.out.println("buy sunscreen clicked");
        }
    }
}
