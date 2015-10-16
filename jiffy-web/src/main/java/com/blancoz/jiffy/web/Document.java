package com.blancoz.jiffy.web;

import com.blancoz.jiffy.util.IOUtils;

import java.io.IOException;
import java.io.File;
import java.util.HashMap;

/**
 *   This class provides some basic properties and functions that
 *   every document should have.
 */
public abstract class Document {

  private final DocumentType type;
  protected String name;


  // Static map for file extensions
  protected static final HashMap<DocumentType, String> fileExtensions;
  static  {
    fileExtensions = new HashMap<DocumentType, String>();
    fileExtensions.put(DocumentType.HTML_DOCUMENT, ".html");
    fileExtensions.put(DocumentType.CSS_STYLESHEET, ".css");
    fileExtensions.put(DocumentType.JAVASCRIPT, ".js");
  }

  public Document(String name) {
    this(name, null);
  }

  public Document(String name, DocumentType type) {
    if(name == null) { throw new IllegalArgumentException("Document name cannot be null"); }
    this.name = name;
    this.type = type;
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

  public String getFileName() {
    return name + fileExtensions.get(getDocumentType());
  }

  public DocumentType getDocumentType() {
    return type;
  }

  public abstract String toString();
  public File writeToFile(String directory) throws IOException {
    return IOUtils.writeToFile(directory, this.getFileName(), toString());
  }
}
