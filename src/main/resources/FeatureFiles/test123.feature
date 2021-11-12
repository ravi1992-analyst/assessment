Feature: Shop Moisturizers OR Sunscreens based on temperature

  Scenario: Buy Moisturizers OR Sunscreens based on temperature
    Given User is on home page
    And User verifies the title "Current Temperature" of the page
    When User clicks on Buy Moisturizers button OR Buy Sunscreens button based on current temperature
    And User clicks on add cheapest the product
    Then Product should add to the cart
    When User click on the cart button
    Then User should navigate to checkout page
    And Verify the added product is in the checkout page
    And User clicks on Pay With Card button
    And Verify payment popup is displayed
    And User fills the details
      | Test@testgmail.com | 5555555555554444 | 1022 | 115 | 10409 |
    And User clicks on payment button
    And User gets the payment success message