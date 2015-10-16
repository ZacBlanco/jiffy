package com.blancoz.jiffy.web.html;


import com.blancoz.jiffy.web.Document;
import com.blancoz.jiffy.util.IOUtils;
import com.blancoz.jiffy.web.DocumentType;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public class HtmlDocument extends Document {
  private HtmlElement root;
  private HtmlElement head;
  public HtmlElement body;

  public String getName() {
    return name;
  }

  public HtmlDocument(String name) {
    super(name, DocumentType.HTML_DOCUMENT);
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




}
