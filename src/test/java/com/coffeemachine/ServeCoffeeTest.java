package com.coffeemachine;

import junit.framework.TestCase;

public class ServeCoffeeTest extends TestCase {
    // Tags: sprint:1
    public Actionwords actionwords;
    protected void setUp() throws Exception {
        super.setUp();
        actionwords = new Actionwords();
        actionwords.createBrowser();

    }

    protected void tearDown() throws Exception {
        actionwords.shutdownBrowser();
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