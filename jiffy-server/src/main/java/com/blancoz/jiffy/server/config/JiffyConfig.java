package com.blancoz.jiffy.server.config;

import java.util.HashMap;

public class JiffyConfig {

  private HashMap<String, String> properties = new HashMap<String, String>();

  public int getPort() {
    return Integer.parseInt(properties.get("port"));
  }

  public void setPort(int port) {
    if(port > 0) {
      properties.put("port", port + "");
    }
  }

  public void setProperty(String key, String value) {
    properties.put(key, value);
  }

}
