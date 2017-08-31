package com.coffeemachine;

import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;


public class ServeCoffeeTest extends TestCase {
    // Tags: sprint:1
    public Actionwords actionwords;
    public WebDriver driver;

    protected void setUp() throws Exception {
        super.setUp();

        driver = new SeleniumDriverGetter().getDriver();
        actionwords = new Actionwords(driver);

    }

    protected void tearDown() throws Exception {
        driver.quit();
    }

    // Well, sometimes, you just get a coffee.
    // Tags: priority:0
    public void testSimpleUse() {
        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // When I take a coffee
        actionwords.iTakeACoffee();
        // Then coffee should be served
        actionwords.coffeeShouldBeServed();
    }
}