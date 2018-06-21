package com.coffeemachine;

import com.saucelabs.saucerest.SauceREST;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;


public class SauceLabsHelper extends CloudHelper{
  private String sessionId;
  private SauceREST sauceREST;

  public SauceLabsHelper() {
    String username = System.getenv("REMOTE_DRIVER_USERNAME");
    String accessKey = System.getenv("REMOTE_DRIVER_PASSWORD");
    this.sauceREST = new SauceREST(username, accessKey);
  }

  public void setDriver(RemoteWebDriver driver) {
    this.sessionId = driver.getSessionId().toString();
  }

  public void setScore(String score) {
    boolean passed = (score == "");
    Map<String, Object> updates = new HashMap<String, Object>();
    updates.put("passed", true);

    sauceREST.updateJobInfo(sessionId, updates);
  }
}
