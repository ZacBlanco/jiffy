package com.blancoz.jiffy.web.js;


import com.blancoz.jiffy.util.IOUtils;
import com.blancoz.jiffy.web.Document;
import com.blancoz.jiffy.web.DocumentType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class JavaScriptDocument extends Document{

  private File javascriptFile;

  public JavaScriptDocument(File file) {
    super(stripExtension(file), DocumentType.JAVASCRIPT);
    if(!file.exists()) { throw new IllegalArgumentException("Could not find file"); }
    javascriptFile = file;
  }

  private static String stripExtension(File file) {
    if(file == null) { throw new IllegalArgumentException("File cannot be null"); }
    String filename = file.getName();
    if(filename != null) {
      int pos = filename.lastIndexOf('.');
      String name = filename;
      if(pos > 0) {
        name = filename.substring(0, pos);
      }
      return name;
    } else { throw new NullPointerException(); }
  }

  @Override
  public void setName(String name) {
    if(name !=null) {
      boolean renamed = javascriptFile.renameTo(new File(javascriptFile.getParent() + "/" + name + ".js"));
      if(renamed){
        this.name = name;
      }
    } else {
      return;
    }
  }

  @Override
  public String toString() {
    try {
      return IOUtils.readFile(javascriptFile.getAbsolutePath());
    } catch (IOException e) {
      return null;
    }
  }
}
