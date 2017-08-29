package com.coffeemachine.display_errors;

import junit.framework.TestCase;
import com.coffeemachine.Actionwords;

public class GroundsTest extends TestCase {

    public Actionwords actionwords;
    protected void setUp() throws Exception {
        super.setUp();
        actionwords = new Actionwords();
        actionwords.createBrowser();

        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // And I handle everything except the grounds
        actionwords.iHandleEverythingExceptTheGrounds();
    }

    protected void tearDown() throws Exception {
        actionwords.shutdownBrowser();
    }

    // 
    // Tags: priority:0
    public void testMessageEmptyGroundsIsDisplayedAfter30CoffeesAreTaken() {
        // When I take "30" coffees
        actionwords.iTakeCoffeeNumberCoffees(30);
        // Then message "Empty grounds" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Empty grounds");
    }
    // 
    // Tags: priority:1
    public void testWhenTheGroundsAreEmptiedMessageIsRemoved() {
        // When I take "30" coffees
        actionwords.iTakeCoffeeNumberCoffees(30);
        // And I empty the coffee grounds
        actionwords.iEmptyTheCoffeeGrounds();
        // Then message "Ready" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Ready");
    }
}