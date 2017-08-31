package com.coffeemachine.display_errors;

import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;

import com.coffeemachine.Actionwords;
import com.coffeemachine.SeleniumDriverGetter;

public class BeansTest extends TestCase {

    public Actionwords actionwords;
    public WebDriver driver;

    protected void setUp() throws Exception {
        super.setUp();

        driver = new SeleniumDriverGetter().getDriver();
        actionwords = new Actionwords(driver);

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // And I handle everything except the beans
        actionwords.iHandleEverythingExceptTheBeans();
    }

    protected void tearDown() throws Exception {
        driver.quit();
    }

    // 
    // Tags: priority:0
    public void testMessageFillBeansIsDisplayedAfter38CoffeesAreTaken() {
        // When I take "38" coffees
        actionwords.iTakeCoffeeNumberCoffees(38);
        // Then message "Fill beans" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Fill beans");
    }
    // 
    // Tags: priority:2
    public void testItIsPossibleToTake40CoffeesBeforeThereIsReallyNoMoreBeans() {
        // When I take "40" coffees
        actionwords.iTakeCoffeeNumberCoffees(40);
        // Then coffee should be served
        actionwords.coffeeShouldBeServed();
        // When I take a coffee
        actionwords.iTakeACoffee();
        // Then coffee should not be served
        actionwords.coffeeShouldNotBeServed();
        // And message "Fill beans" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Fill beans");
    }
    // 
    // Tags: priority:0
    public void testAfterAddingBeansTheMessageFillBeansDisappears() {
        // When I take "40" coffees
        actionwords.iTakeCoffeeNumberCoffees(40);
        // And I fill the beans tank
        actionwords.iFillTheBeansTank();
        // Then message "Ready" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Ready");
    }
}