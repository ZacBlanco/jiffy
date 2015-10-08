package com.blancoz.jiffy.web.html;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class HtmlElementTest {

  @Test
  public void basicElementTest() throws Exception {

    String name = "elementName";
    String id = "elementId";
    HtmlElement e = new HtmlElement(name);

    assertTrue("close tag not correct", e.getCloseTag().equals("</" + name + ">"));
    assertTrue("Open tag not correct",e.getOpenTag().equals("<" + name + " >"));
    assertTrue("id not present", e.setId(id).toString().contains("id=\"" + id + "\""));
    assertTrue("Content not present", e.addContent("Test-Content").toString().contains("Test-Content"));
    assertTrue("attibute not present", e.addAttriute("style", ".a{}").toString().contains("style=\".a{}\""));
    assertFalse("attribute not supposed to me present",
        e.addAttriute("style", "test").deleteAttribute("style").toString().contains("style"));

  }

  @Test
  public void childElementTest() throws Exception {

    String element1 = "el1";
    String element2 = "el2";

    HtmlElement el1 = new HtmlElement(element1);
    HtmlElement el2 = new  HtmlElement(element2).setId("2");
    el1.addElement(el2);

    assertTrue("Elements not equal", el1.getChildByName("el2") == el2);
    assertTrue(el1.getId() == null);
    assertTrue(el2.getId().equals("2"));
    assertTrue(el2.addContent("Test-Content").toString().contains("Test-Content"));

  }




}