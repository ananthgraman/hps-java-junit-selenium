package com.coffeemachine;

import junit.framework.TestCase;

public class ServeCoffeeTest extends TestCase {
    // Tags: sprint:1
    public Actionwords actionwords = new Actionwords();
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