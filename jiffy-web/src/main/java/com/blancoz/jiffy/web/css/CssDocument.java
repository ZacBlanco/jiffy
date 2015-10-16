package com.blancoz.jiffy.web.css;

import com.blancoz.jiffy.util.IOUtils;
import com.blancoz.jiffy.web.Document;
import com.blancoz.jiffy.web.DocumentType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class CssDocument extends Document{

  HashMap<String, CssSelector> selectors;
  public CssDocument(String name) {
    super(name, DocumentType.CSS_STYLESHEET);
    selectors = new HashMap<String, CssSelector>();
  }

  public String getName() {
    return name;
  }

  public void removeSelector(CssSelector selector) {
    selectors.remove(selector.getSelector());
  }

  public void removeSelector(String selector) {
    selectors.remove(selector);
  }

  public void addSelector(CssSelector selector) {
    selectors.put(selector.getSelector(), selector);
  }

  public CssSelector getSelector(String selector) throws NoSuchElementException {
    if(selectors.containsKey(selector)) {
      return selectors.get(selector);
    }else {
      throw new NoSuchElementException("Could not find CSS selector describing " + selector);
    }
  }

  public String toString() {
    String prt = "";
    for( Map.Entry<String, CssSelector> e: selectors.entrySet()) {
      prt += e.getValue().toString();
      prt += "\r\n";
    }
    return prt;
  }

}
