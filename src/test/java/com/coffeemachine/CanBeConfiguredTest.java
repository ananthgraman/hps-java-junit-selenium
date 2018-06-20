package com.coffeemachine;

import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class CanBeConfiguredTest extends TestCase {
    // Tags: sprint:2
    public Actionwords actionwords;
    public WebDriver driver;
    private CBTHelper cbt;
    public String score = "fail";
    public String featureName = "Can be configured";

    protected void setUp() throws Exception {
        super.setUp();
        actionwords = new Actionwords();
    }

    protected void scenarioSetup(String testName)  throws Exception {
        driver = new SeleniumDriverGetter().getDriver(featureName, testName);
        if (System.getenv("USE_CBT") != null) {
            cbt = new CBTHelper();
            cbt.setSessionId(((RemoteWebDriver)driver).getSessionId().toString());
        }
        actionwords.setDriver(driver);

    }

    protected void tearDown() throws Exception {
        if (System.getenv("USE_CBT") != null) {
            cbt.setScore(score);
        }
        driver.quit();
    }

    // 
    // Tags: priority:1
    public void testDisplaySettings() throws Exception {
        scenarioSetup("Display settings");

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // When I switch to settings mode
        actionwords.iSwitchToSettingsMode();
        // Then displayed message is:
        actionwords.displayedMessageIs("Settings:\n - 1: water hardness\n - 2: grinder");
        score = "pass";
    }
    // 
    // Tags: priority:0
    public void testDefaultSettings() throws Exception {
        scenarioSetup("Default settings");

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // When I switch to settings mode
        actionwords.iSwitchToSettingsMode();
        // Then settings should be:
        actionwords.settingsShouldBe("| water hardness | 2      |\n| grinder        | medium |");
        score = "pass";
    }
}