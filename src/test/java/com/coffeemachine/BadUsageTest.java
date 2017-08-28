package com.coffeemachine;

import junit.framework.TestCase;

public class BadUsageTest extends TestCase {

    public Actionwords actionwords = new Actionwords();
    // You keep getting coffee even if the "Empty grounds" message is displayed. That said it's not a fantastic idea, you'll get ground everywhere when you'll decide to empty it.
    // Tags: priority:2
    public void testFullGroundsDoesNotBlockCoffee() {
        // Given the coffee machine is started
        actionwords.theCoffeeMachineIsStarted();
        // And I handle everything except the grounds
        actionwords.iHandleEverythingExceptTheGrounds();
        // When I take "50" coffees
        actionwords.iTakeCoffeeNumberCoffees(50);
        // Then message "Empty grounds" should be displayed
        actionwords.messageMessageShouldBeDisplayed("Empty grounds");
        // And coffee should be served
        actionwords.coffeeShouldBeServed();
    }
}