package com.coffeemachine.display_errors;

import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;

import com.coffeemachine.Actionwords;
import com.coffeemachine.SeleniumDriverGetter;

public class WaterTest extends TestCase {

    public Actionwords actionwords;
    public WebDriver driver;

    protected void setUp() throws Exception {
        super.setUp();

        driver = new SeleniumDriverGetter().getDriver();
        actionwords = new Actionwords(driver);

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // And I handle everything except the water tank
        actionwords.iHandleEverythingExceptTheWaterTank();
    }

    protected void tearDown() throws Exception {
        driver.quit();
    }

    // 
    // Tags: priority:0
    public void testMessageFillWaterTankIsDisplayedAfter50CoffeesAreTaken() {
        // When I take "50" coffees
        actionwords.iTakeCoffeeNumberCoffees(50);
        // Then message "Fill tank" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Fill tank");
    }
    // 
    // Tags: priority:2
    public void testItIsPossibleToTake10CoffeesAfterTheMessageFillWaterTankIsDisplayed() {
        // When I take "60" coffees
        actionwords.iTakeCoffeeNumberCoffees(60);
        // Then coffee should be served
        actionwords.coffeeShouldBeServed();
        // When I take a coffee
        actionwords.iTakeACoffee();
        // Then coffee should not be served
        actionwords.coffeeShouldNotBeServed();
    }
    // 
    // Tags: priority:0
    public void testWhenTheWaterTankIsFilledTheMessageDisappears() {
        // When I take "55" coffees
        actionwords.iTakeCoffeeNumberCoffees(55);
        // And I fill the water tank
        actionwords.iFillTheWaterTank();
        // Then message "Ready" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Ready");
    }
}