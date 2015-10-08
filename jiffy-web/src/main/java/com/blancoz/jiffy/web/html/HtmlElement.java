package com.blancoz.jiffy.web.html;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

public class HtmlElement {

  private String elementName;
  private HashMap<String, String> attributes;
  private LinkedList<HtmlElement> children;
  private String id;
  private HtmlElement parent;
  private String content = "";


  public HtmlElement(String name, String content) {
    this(name);
    addContent(content);
  }

  public HtmlElement(String name) {

    attributes = new HashMap<String, String>();
    children = new LinkedList<HtmlElement>();
    if(name != null) {
      this.elementName = name;
    } else {
      throw new NullPointerException();
    }
  }


  private HtmlElement setName(String s) {
    if(s != null) {
      this.elementName = s;
    } else {
      throw new NullPointerException();
    }
    return this;
  }

  public HtmlElement getParent() throws NullPointerException {
    if(parent != null) {
      return parent;
    }
    throw new NullPointerException("Parent is null or does not exist");
  }

  private HtmlElement setParent(HtmlElement newParent) {
    if(parent != null) {
      parent = newParent;
    }
    return this;
  }

  public String getId() {
    return id;
  }

  public HtmlElement setId(String id) {
    this.id = id;
    addAttriute("id", id, true);
    return this;
  }

  public HtmlElement addAttriute(String attribute, String value) {
    addAttriute(attribute, value, true);
    return this;
  }

  public HtmlElement addAttriute(String attribute, String value, boolean overwrite) {
    if(attribute != null && value != null) {
      if(overwrite) {
        attributes.put(attribute, value);
      } else if(!attributes.containsKey(attribute)) {
        attributes.put(attribute, value);
      }
    }
    return this;
  }

  public HtmlElement deleteAttribute(String attribute) {
    attributes.remove(attribute);
    return this;
  }

  public HtmlElement addElement(HtmlElement e) throws NullPointerException {
    if(e != null) {
      e.setParent(this);
      children.add(e);
      return e;
    } else {
      throw new NullPointerException("Added element was null");
    }
  }

  // Returns the first occurance of the specified element
  public HtmlElement getChildByName(String name) throws NoSuchElementException {
    for (HtmlElement e : children) {
      if(e.elementName.equals(name)) {
        return e;
      }
    }
    throw new NoSuchElementException("Element does not exist");
  }

  public String getOpenTag() {
    String ret = "<" + elementName + " ";
    for (Map.Entry<String, String> entry : attributes.entrySet()) {
      if(!entry.getValue().equals("")) {
        ret += entry.getKey() + "=\"" + entry.getValue() + "\" ";
      } else {
        ret += entry.getKey() + " ";
      }
    }
    ret += ">";
    return ret;
  }

  public HtmlElement addContent(String content) {
    this.content += content;
    return this;
  }

  public HtmlElement resetContent() {
    content = "";
    return this;
  }

  public String getCloseTag() {
    return "</" + elementName + ">";
  }

  public String toString() {
    String element = "";
    element += getOpenTag() + "\n";
    element += content;
    for (HtmlElement e : children) {
      element += e.toString();
    }
    element += getCloseTag() + "\n";
    return element;
  }


}

