package com.coffeemachine;

import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.coffeemachine.Actionwords;
import com.coffeemachine.SeleniumDriverGetter;

public class WaterTest extends TestCase {

    public Actionwords actionwords;
    public WebDriver driver;
    private CBTHelper cbt;
    public String score = "fail";
    public String featureName = "Water";

    protected void setUp() throws Exception {
        super.setUp();
        cbt = new CBTHelper();
        actionwords = new Actionwords();
    }

    protected void tearDown() throws Exception {
        cbt.setScore(score);
        driver.quit();
    }

    protected void scenarioSetup() {

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // And I handle everything except the water tank
        actionwords.iHandleEverythingExceptTheWaterTank();
    }

    // 
    // Tags: priority:0
    public void testMessageFillWaterTankIsDisplayedAfter50CoffeesAreTaken() throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, "Message \"Fill water tank\" is displayed after 50 coffees are taken");
        cbt.setSessionId(((RemoteWebDriver)driver).getSessionId().toString());
        actionwords.setDriver(driver);
        scenarioSetup();

        // When I take "50" coffees
        actionwords.iTakeCoffeeNumberCoffees(50);
        // Then message "Fill tank" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Fill tank");
        score = "pass";
    }
    // 
    // Tags: priority:2
    public void testItIsPossibleToTake10CoffeesAfterTheMessageFillWaterTankIsDisplayed() throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, "It is possible to take 10 coffees after the message \"Fill water tank\" is displayed");
        cbt.setSessionId(((RemoteWebDriver)driver).getSessionId().toString());
        actionwords.setDriver(driver);
        scenarioSetup();

        // When I take "60" coffees
        actionwords.iTakeCoffeeNumberCoffees(60);
        // Then coffee should be served
        actionwords.coffeeShouldBeServed();
        // When I take a coffee
        actionwords.iTakeACoffee();
        // Then coffee should not be served
        actionwords.coffeeShouldNotBeServed();
        score = "pass";
    }
    // 
    // Tags: priority:0
    public void testWhenTheWaterTankIsFilledTheMessageDisappears() throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, "When the water tank is filled, the message disappears");
        cbt.setSessionId(((RemoteWebDriver)driver).getSessionId().toString());
        actionwords.setDriver(driver);
        scenarioSetup();

        // When I take "55" coffees
        actionwords.iTakeCoffeeNumberCoffees(55);
        // And I fill the water tank
        actionwords.iFillTheWaterTank();
        // Then message "Ready" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Ready");
        score = "pass";
    }
}