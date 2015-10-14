package com.blancoz.jiffy.server.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class JiffyConfig {

  static final String PROPERTY_PORT = "jiffy.port";
  static final String PROPERTY_PROTOCOL = "jiffy.protocol";
  static final String PROPERTY_MAX_PORT = "jiffy.port.max";
  static final String PROPERTY_MIN_PORT = "jiffy.port.min";

  static final int DEFAULT_MAX_PORT = 65535;
  static final int DEFAULT_MIN_PORT = 1025;
  static final int DEFAULT_PORT = 7070;
  static final String DEFAULT_PROTOCOL = "http";

  private Properties properties = new Properties();


  //Any new properties added MUST have a default and be added to the defaults property list.
  private static Properties defaults = new Properties();
  static {
    defaults.setProperty(PROPERTY_PORT, DEFAULT_PORT + "");
    defaults.setProperty(PROPERTY_MIN_PORT, DEFAULT_MIN_PORT + "");
    defaults.setProperty(PROPERTY_MAX_PORT, DEFAULT_MAX_PORT + "");
    defaults.setProperty(PROPERTY_PROTOCOL, DEFAULT_PROTOCOL);
  }

  private String getProperty(String prop) throws NullPointerException {

    if(properties.containsKey(prop)) {
      return properties.getProperty(prop);
    } else if(defaults.containsKey(prop)) {
      return defaults.getProperty(prop);
    } else {
      throw new NullPointerException("Property key " + prop + " not found in properties or defaults");
    }
  }

  private void setProperty(String property, String value) throws NullPointerException {
    if(property != null && value != null) {
      properties.setProperty(property, value);
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

    int maxPort = Integer.parseInt(getProperty(PROPERTY_MAX_PORT));
    int minPort = Integer.parseInt(getProperty(PROPERTY_MIN_PORT));
    if(port <= maxPort && port >= minPort) {
      setProperty(PROPERTY_PORT, port + "");
    } else {
      throw new IllegalArgumentException("Port must be between: " + minPort + " and " + maxPort);
    }
  }

  public void setProtocol(String protocol) throws IllegalArgumentException {
    if(protocol.equals("http") || protocol.equals("https")) {
      setProperty(PROPERTY_PROTOCOL, protocol);
    } else {
      throw new IllegalArgumentException("protocol must be \"http\" or \"https\"");
    }
  }

  public String getProtocol() {
    return getProperty(PROPERTY_PROTOCOL);
  }

  //Reads a simple config file line-by-line. Any line that is blank or starts with "#" will be ignored
  //Format should be in the explicit form: "{jiffy-property} {property-value}";
  public void readConfigFile(String filePath) throws FileNotFoundException, IOException{
    File f = new File(filePath);
    if(f.exists()) {
      BufferedReader reader = new BufferedReader(new FileReader(f));
      String line;
      while((line = reader.readLine()) != null) {

        //We just ignore any lines that will give us errors or don't have keys present in the default properties
        if(!line.startsWith("#") && !line.trim().isEmpty()) {
          int space = line.indexOf(" ");
          if(space > 0 && space != line.length() - 1) {
            String property = line.substring(0, space);
            String value = line.substring(space + 1);
            if(defaults.containsKey(property) && !value.isEmpty()) {
              setProperty(property, value);
            }
          }
        }


      }
    } else {
      throw new FileNotFoundException("Could not locate file at " + filePath);
    }

  }


}
