package com.coffeemachine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.net.MalformedURLException;

public class SeleniumDriverGetter {

    public WebDriver getDriver(String featureName, String testName) throws Exception {
        DesiredCapabilities caps = DesiredCapabilities.chrome();

        caps.setCapability("name", "Coffee machine - " + featureName + "/" + testName);
        caps.setCapability("build", System.getenv("TRAVIS_BUILD_NUMBER"));

        if (System.getenv("USE_CBT") != null) {
          caps.setCapability("browserName", "Chrome");
          caps.setCapability("version", "66x64");
          caps.setCapability("platform", "Windows 10");
          caps.setCapability("screenResolution", "1366x768");
          caps.setCapability("record_video", "true");
        }

        if (System.getenv("USE_SAUCELABS") != null) {
          caps.setCapability("platform", "Windows 10");
          caps.setCapability("version", "latest");
        }

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
        String authkey = System.getenv("REMOTE_DRIVER_PASSWORD");
        String driverUrl = System.getenv("REMOTE_DRIVER_URL");
        URL hubUrl = null;

        try {
          hubUrl = new URL("http://" + username + ":" + authkey + "@" + driverUrl);
        } catch (MalformedURLException e) {
          System.out.println("Invalid HUB URL");
        }

        return new RemoteWebDriver(hubUrl, caps);
    }
}
