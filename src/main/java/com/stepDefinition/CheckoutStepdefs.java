package com.stepDefinition;

import com.driverFactory.DriverFactory;
import com.pages.Checkout;
import com.qaUtils.ConfigReader;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class CheckoutStepdefs {
    private Checkout checkout = new Checkout(DriverFactory.getDriver());


    @Then("User should navigate to checkout page")
    public void user_should_navigate_to_checkout_page() {
        Assert.assertTrue(checkout.isOnCheckPage());
    }

    @Then("Verify the added product is in the checkout page")
    public void verify_the_added_product_is_in_the_checkout_page() {
        String pName = checkout.getProductName();
        if (pName != null) {
            System.out.println(pName);
        } else {
            Assert.fail("No product is added");
        }
    }

    @Then("User clicks on Pay With Card button")
    public void user_clicks_on_pay_with_card_button() {
        checkout.clickOnPayLink();
    }

    @Then("Verify payment popup is displayed")
    public void verify_payment_popup_is_displayed() {
        Assert.assertTrue(checkout.isOnCheckPage());
    }

    @Then("User fills the details")
    public void user_fills_the_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<String> list = dataTable.asList();
        for (String a : list) {
            System.out.println(a);
        }
        checkout.enterCardDetail(list);
    }

    @Then("User clicks on payment button")
    public void user_clicks_on_payment_button() {
        checkout.clickOnPayBtn();
    }

    @Then("User gets the payment success message")
    public void user_gets_the_payment_success_message() {
        checkout.getPaymentConfirmation();
    }
}
