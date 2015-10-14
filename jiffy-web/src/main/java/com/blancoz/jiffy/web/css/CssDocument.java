package com.blancoz.jiffy.web.css;

import java.util.HashMap;

public class CssDocument {

  HashMap<String, CssSelector> selectors;

  public CssDocument() {
    selectors = new HashMap<String, CssSelector>();
  }

  void removeSelector(CssSelector selector) {
    selectors.remove(selector.selector);
  }

  void updateSelector(CssSelector selector) {

  }

  void addSelector(CssSelector selector) {

  }

  public String toString() {
    return null;
  }

  void toFile() {

  }

}
