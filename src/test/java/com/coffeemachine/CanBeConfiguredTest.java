package com.coffeemachine;

import junit.framework.TestCase;

public class CanBeConfiguredTest extends TestCase {
    // Tags: sprint:2
    public Actionwords actionwords = new Actionwords();
    // 
    // Tags: priority:1
    public void testDisplaySettings() {
        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // When I switch to settings mode
        actionwords.iSwitchToSettingsMode();
        // Then displayed message is:
        actionwords.displayedMessageIs("Settings:\n - 1: water hardness\n - 2: grinder");
    }
    // 
    // Tags: priority:0
    public void testDefaultSettings() {
        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // When I switch to settings mode
        actionwords.iSwitchToSettingsMode();
        // Then settings should be:
        actionwords.settingsShouldBe("| water hardness | 2      |\n| grinder        | medium |");
    }
}