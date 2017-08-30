package com.coffeemachine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.ChromeDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class SeleniumDriverGetter {
    public WebDriver getDriver() throws Exception {
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        //caps.setCapability("platform", "Windows XP");
        //caps.setCapability("version", "51.0");
        //caps.setCapability("marionette", "false");

        if (System.getenv("USE_REMOTE_DRIVER") != null) {
            return getRemoteDriver(caps);
        }
        return getLocalDriver(caps);
    }

    private WebDriver getLocalDriver(DesiredCapabilities caps) {
        return new ChromeDriver(caps);
    }

    private WebDriver getRemoteDriver(DesiredCapabilities caps) throws Exception {
        String username = System.getenv("REMOTE_DRIVER_USERNAME");
        String password = System.getenv("REMOTE_DRIVER_PASSWORD");
        String driverUrl = "https://" + username + ":" + password + System.getenv("REMOTE_DRIVER_URL");

        return new RemoteWebDriver(new URL(driverUrl), caps);
    }
}
