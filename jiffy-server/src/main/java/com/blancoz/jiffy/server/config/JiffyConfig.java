package com.blancoz.jiffy.server.config;

import java.util.Properties;

public class JiffyConfig {

  static final String PROPERTY_PORT = "jiffy.port";
  static final int MAX_PORT = 65535;
  static final int MIN_PORT = 1025;

  private Properties propList = new Properties();

  static final int JIFFY_DEFAULT_PORT = 7090;

  private String getProperty(String prop) throws NullPointerException {
    return System.getProperty(prop);
  }

  private void setProperty(String property, String value) throws NullPointerException {
    if(property != null && value != null) {
      propList.setProperty(property, value);
    } else if(property == null) {
      throw new NullPointerException("Property cannot be null");
    } else {
      throw new NullPointerException("Property value cannot be null");
    }
  }

  public int getPort() {
    String port = getProperty(PROPERTY_PORT);
    return Integer.parseInt(port);
  }

  public void setPort(int port) {
    if(port <= MAX_PORT && port >= MIN_PORT) {
      propList.setProperty(PROPERTY_PORT, port + "");
    } else {
      throw new IllegalArgumentException("Port must be between: " + MIN_PORT + " and " + MAX_PORT);
    }
  }


}
