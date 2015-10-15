package com.blancoz.jiffy.web.css;

import java.util.HashMap;
import java.util.Map;

public class CssSelector {
  private String selector;
  private String name;
  HashMap<String, String> properties;

  public CssSelector(String selector, HashMap<String, String> properties) {
    this.selector = selector;
    this.properties = properties;
  }

  public CssSelector(String selector) {
    this.selector = selector;
    properties = new HashMap<String, String>();
  }

  public String getSelector() {
    return selector;
  }

  public void addProperty(String name, String value) {
    if(name != null && value != null) {
      properties.put(name, value);
    } else {
      throw new NullPointerException("Name or value of css property cannot be null");
    }
  }

  public void removeProperty(String name) {
    properties.remove(name);
  }

  public boolean hasProperty(String property) {
    return properties.containsKey(property);
  }

  public boolean hasPropertyValue(String propValue) {
    return properties.containsValue(propValue);
  }

  public void setSelector(String selector) {
    if(selector != null) {
      this.selector = selector;
    } else {
      throw new NullPointerException("css selector cannot be null");
    }
  }

  public HashMap<String, String> getProperties() {
    return properties;
  }

  public String toString() {
    String all = this.selector + " {\r\n";
    for(Map.Entry<String, String> e : properties.entrySet()) {
      all += e.getKey() + ":" + e.getValue() + ";\r\n";
    }
    all += "}\r\n";

    return all;
  }

}
