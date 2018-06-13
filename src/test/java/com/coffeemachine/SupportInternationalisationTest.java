package com.coffeemachine;

import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class SupportInternationalisationTest extends TestCase {
    // Tags: sprint:3
    public Actionwords actionwords;
    public WebDriver driver;
    private CBTHelper cbt;
    public String score = "fail";
    public String featureName = "Support internationalisation";

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

    }

    // 
    // Tags: priority:1
    public void testNoMessagesAreDisplayedWhenMachineIsShutDown() throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, "No messages are displayed when machine is shut down");
        cbt.setSessionId(((RemoteWebDriver)driver).getSessionId().toString());
        actionwords.setDriver(driver);
        scenarioSetup();

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // When I shutdown the coffee machine
        actionwords.iShutdownTheCoffeeMachine();
        // Then message "" should be displayed
        actionwords.messageMessageShouldBeDisplayed("");
        score = "pass";
    }
    public void messagesAreBasedOnLanguage(String language, String readyMessage) throws Exception {
        // Tags: priority:1
        // Well, sometimes, you just get a coffee.
        driver = new SeleniumDriverGetter().getDriver(featureName, "Messages are based on language");
        cbt.setSessionId(((RemoteWebDriver)driver).getSessionId().toString());
        actionwords.setDriver(driver);
        scenarioSetup();
        // When I start the coffee machine using language "<language>"
        actionwords.iStartTheCoffeeMachineUsingLanguageLang(language);
        // Then message "<ready_message>" should be displayed
        actionwords.messageMessageShouldBeDisplayed(readyMessage);
    }

    public void testMessagesAreBasedOnLanguageEnglish() throws Exception {
        messagesAreBasedOnLanguage("en", "Ready");
    }

    public void testMessagesAreBasedOnLanguageFrench() throws Exception {
        messagesAreBasedOnLanguage("fr", "Pret");
    }
}