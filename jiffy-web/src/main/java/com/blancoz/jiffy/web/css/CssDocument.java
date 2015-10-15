package com.blancoz.jiffy.web.css;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class CssDocument {

  HashMap<String, CssSelector> selectors;
  private String name;
  public CssDocument(String name) {
    if(name != null ) {
      this.name = name;
    } else {
      name = "_default_css";
    }
    selectors = new HashMap<String, CssSelector>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if(name !=null) {
      this.name = name;
    } else {
      return;
    }
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


  public File toFile(String directory) throws IOException {
    File dirs = new File(directory);

    if(!dirs.exists()) {
      if(!dirs.mkdirs()) {
        throw new IOException("Could not create directory structure " + directory);
      }
    }

    File f = new File(dirs.getAbsolutePath() + "/" + getName() + ".css");

    if(f.exists() && f.canWrite()) {
      return writeFile(f, this);
    } else if (f.createNewFile() && f.canWrite()) {
      return writeFile(f, this);
    } else {
      throw new IOException("Could not write file to location " + directory );
    }
  }

  private static File writeFile(File f, CssDocument d) throws IOException {
    FileOutputStream fsout = new FileOutputStream(f);
    fsout.write(d.toString().getBytes());
    fsout.close();
    return f;
  }

}
