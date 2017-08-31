package com.coffeemachine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class SeleniumDriverGetter {

    public WebDriver getDriver() throws Exception {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        //caps.setCapability("platform", "Windows XP");
        //caps.setCapability("version", "51.0");
        //caps.setCapability("marionette", "false");

        if (System.getenv("USE_REMOTE_DRIVER") != null) {
            return getRemoteDriver(caps);
        }
        return getLocalDriver(caps);
    }

    private WebDriver getLocalDriver(DesiredCapabilities caps) {
        if (System.getenv("USE_HEADLESS_CHROME") != null) {
          String chromePath = System.getenv("CHROME_PATH");
          if (chromePath == null) {
            chromePath = "/usr/bin/google-chrome-stable";
          }

          final ChromeOptions chromeOptions = new ChromeOptions();
          chromeOptions.setBinary(chromePath);
          chromeOptions.addArguments("--headless");
          chromeOptions.addArguments("--disable-gpu");

          caps.setJavascriptEnabled(true);
          caps.setCapability(
              ChromeOptions.CAPABILITY, chromeOptions
          );
        }

        return new ChromeDriver(caps);
    }

    private WebDriver getRemoteDriver(DesiredCapabilities caps) throws Exception {
        String username = System.getenv("REMOTE_DRIVER_USERNAME");
        String password = System.getenv("REMOTE_DRIVER_PASSWORD");
        String driverUrl = "https://" + username + ":" + password + System.getenv("REMOTE_DRIVER_URL");

        return new RemoteWebDriver(new URL(driverUrl), caps);
    }
}
