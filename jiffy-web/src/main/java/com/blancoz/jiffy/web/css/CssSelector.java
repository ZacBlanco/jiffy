package com.blancoz.jiffy.web.css;


import java.util.HashMap;

public class CssSelector {
  String selector;

  HashMap<String, String> properties;

  public CssSelector(String selector, HashMap<String, String> properties) {
    this.selector = selector;
    this.properties = properties;
  }

  public CssSelector(String selector) {
    this.selector = selector;
    properties = new HashMap<String, String>();
  }

  public void addProperties(String name, String value) {
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
    return properties.containsKey(propValue);
  }

  public void setSelector(String selector) {
    if(selector != null) {
      this.selector = selector;
    } else {
      throw new NullPointerException("css selector cannot be null");
    }
  }


}
