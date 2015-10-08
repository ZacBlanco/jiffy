package com.blancoz.jiffy.web.html;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

public class HtmlDocument {
  final private String name;
  private HtmlElement root;
  private HtmlElement head;
  public HtmlElement body;
  private String title;

  public String getName() {
    return name;
  }

  public HtmlDocument(String name) {
    if(name == null) { throw new NullPointerException("Name in HtmlDocument cannot be null"); }
    this.name = name;
    root = createDocumentStructure();
    setTitle(name);
  }

  public void setTitle(String title)  {
    if(title != null) {
      try {
        head.getChildByName("title").resetContent().addContent(title);
        return;
      } catch (NoSuchElementException e) {

        HtmlElement titleElement = new HtmlElement("title");
        titleElement.addContent(title);
        head.addElement(titleElement);
        return;
      }
    }
    throw new NullPointerException();
  }

  private HtmlElement createDocumentStructure() {
    HtmlElement root = new HtmlElement("html");
    HtmlElement head = new HtmlElement("head");
    HtmlElement body = new HtmlElement("body");
    this.head = head;
    this.body = body;
    root.addElement(head);
    root.addElement(body);
    return root;
  }

  private HtmlElement createHead() {
    return head;
  }

  public HtmlElement addContent(String content) {
    body.addContent(content);
    return body;
  }


  public HtmlElement addContent(HtmlElement element) {
    body.addElement(element);
    return element;
  }

  public String toString() {
    String ret = "<!DOCTYPE html>\r\n";
    ret += root.toString();
    return ret;
  }

  public File toFile(String directory) throws IOException {
    File dirs = new File(directory);

    if(!dirs.exists()) {
      if(!dirs.mkdirs()) {
        throw new IOException("Could not create directory structure " + directory);
      }
    }

    File f = new File(dirs.getAbsolutePath() + "/" + getName() + ".html");

    if(f.exists() && f.canWrite()) {
      return writeFile(f, this);
    } else if (f.createNewFile() && f.canWrite()) {
      return writeFile(f, this);
    } else {
      throw new IOException("Could not write file to location " + directory );
    }
  }

  private static File writeFile(File f, HtmlDocument d) throws IOException {
    FileOutputStream fsout = new FileOutputStream(f);
    fsout.write(d.toString().getBytes());
    fsout.close();
    return f;
  }





}
