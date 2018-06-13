package com.coffeemachine;

import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.coffeemachine.Actionwords;
import com.coffeemachine.SeleniumDriverGetter;

public class GroundsTest extends TestCase {

    public Actionwords actionwords;
    public WebDriver driver;
    private CBTHelper cbt;
    public String score = "fail";
    public String featureName = "Grounds";

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
        // And I handle everything except the grounds
        actionwords.iHandleEverythingExceptTheGrounds();
    }

    // 
    // Tags: priority:0
    public void testMessageEmptyGroundsIsDisplayedAfter30CoffeesAreTaken() throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, "Message \"Empty grounds\" is displayed after 30 coffees are taken");
        cbt.setSessionId(((RemoteWebDriver)driver).getSessionId().toString());
        actionwords.setDriver(driver);
        scenarioSetup();

        // When I take "30" coffees
        actionwords.iTakeCoffeeNumberCoffees(30);
        // Then message "Empty grounds" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Empty grounds");
        score = "pass";
    }
    // 
    // Tags: priority:1
    public void testWhenTheGroundsAreEmptiedMessageIsRemoved() throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, "When the grounds are emptied, message is removed");
        cbt.setSessionId(((RemoteWebDriver)driver).getSessionId().toString());
        actionwords.setDriver(driver);
        scenarioSetup();

        // When I take "30" coffees
        actionwords.iTakeCoffeeNumberCoffees(30);
        // And I empty the coffee grounds
        actionwords.iEmptyTheCoffeeGrounds();
        // Then message "Ready" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Ready");
        score = "pass";
    }
}