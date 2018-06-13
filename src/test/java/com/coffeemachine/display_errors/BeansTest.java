package com.coffeemachine;

import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.coffeemachine.Actionwords;
import com.coffeemachine.SeleniumDriverGetter;

public class BeansTest extends TestCase {

    public Actionwords actionwords;
    public WebDriver driver;
    private CBTHelper cbt;
    public String score = "fail";
    public String featureName = "Beans";

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
        // And I handle everything except the beans
        actionwords.iHandleEverythingExceptTheBeans();
    }

    // 
    // Tags: priority:0
    public void testMessageFillBeansIsDisplayedAfter38CoffeesAreTaken() throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, "Message \"Fill beans\" is displayed after 38 coffees are taken");
        cbt.setSessionId(((RemoteWebDriver)driver).getSessionId().toString());
        actionwords.setDriver(driver);
        scenarioSetup();

        // When I take "38" coffees
        actionwords.iTakeCoffeeNumberCoffees(38);
        // Then message "Fill beans" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Fill beans");
        score = "pass";
    }
    // 
    // Tags: priority:2
    public void testItIsPossibleToTake40CoffeesBeforeThereIsReallyNoMoreBeans() throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, "It is possible to take 40 coffees before there is really no more beans");
        cbt.setSessionId(((RemoteWebDriver)driver).getSessionId().toString());
        actionwords.setDriver(driver);
        scenarioSetup();

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
        score = "pass";
    }
    // 
    // Tags: priority:0
    public void testAfterAddingBeansTheMessageFillBeansDisappears() throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, "After adding beans, the message \"Fill beans\" disappears");
        cbt.setSessionId(((RemoteWebDriver)driver).getSessionId().toString());
        actionwords.setDriver(driver);
        scenarioSetup();

        // When I take "40" coffees
        actionwords.iTakeCoffeeNumberCoffees(40);
        // And I fill the beans tank
        actionwords.iFillTheBeansTank();
        // Then message "Ready" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Ready");
        score = "pass";
    }
}