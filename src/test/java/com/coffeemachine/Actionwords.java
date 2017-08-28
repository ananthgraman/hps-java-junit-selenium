package com.coffeemachine;

public class Actionwords {

    public void iStartTheCoffeeMachineUsingLanguageLang(String lang) {
        // TODO: Implement action: String.format("Start the coffee machine using language %s", lang)
    }

    public void iShutdownTheCoffeeMachine() {
        // TODO: Implement action: "Shutdown coffee machine"
    }

    public void messageMessageShouldBeDisplayed(String message) {
        // TODO: Implement result: String.format("Displayed message is \"%s\"", message)
    }

    public void coffeeShouldBeServed() {
        // TODO: Implement result: "Coffee is served :)"
    }

    public void coffeeShouldNotBeServed() {
        // TODO: Implement result: "No coffee is served :("
    }

    public void iTakeACoffee() {
        // TODO: Implement action: "Take a coffee"
    }

    public void iEmptyTheCoffeeGrounds() {
        // TODO: Implement action: "Empty coffee grounds"
    }

    public void iFillTheBeansTank() {
        // TODO: Implement action: "Fill beans"
    }

    public void iFillTheWaterTank() {
        // TODO: Implement action: "Fill water tank"
    }

    public void iTakeCoffeeNumberCoffees(int coffeeNumber) {

    }

    public void theCoffeeMachineIsStarted() {
        iStartTheCoffeeMachineUsingLanguageLang("en");
    }

    public void iHandleEverythingExceptTheWaterTank() {
        iHandleCoffeeGrounds();
        iHandleBeans();
    }

    public void iHandleWaterTank() {

    }

    public void iHandleBeans() {

    }

    public void iHandleCoffeeGrounds() {

    }

    public void iHandleEverythingExceptTheBeans() {
        iHandleWaterTank();
        iHandleCoffeeGrounds();
    }

    public void iHandleEverythingExceptTheGrounds() {
        iHandleWaterTank();
        iHandleBeans();
    }

    public void displayedMessageIs(String freeText) {
        messageMessageShouldBeDisplayed(freeText);
    }

    public void iSwitchToSettingsMode() {

    }

    public void settingsShouldBe(String datatable) {

    }
}
