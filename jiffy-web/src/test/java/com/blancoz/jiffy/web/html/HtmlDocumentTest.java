package com.blancoz.jiffy.web.html;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class HtmlDocumentTest {


  public String testDir = "target/" + UUID.randomUUID().toString() + "/";

  @Test
  public void basicDocumentTest() throws Exception {

    HtmlDocument document = new HtmlDocument("TestDoc");
    document.setTitle("TestDoc");
    document.addContent("TestContent");

    assertTrue(document.toString().contains("DOCTYPE"));
    assertTrue(document.getName().equals("TestDoc"));
    assertTrue(document.body.toString().contains("TestContent"));
    try{
      HtmlDocument d = new HtmlDocument(null);
      fail();
    } catch (NullPointerException e) { System.getProperties(); }



  }

  @Test
  public void fileWriterTest() throws Exception {

    HtmlDocument document = new HtmlDocument("TestDoc");
    document.setTitle("TestDoc");
    document.addContent("TestContent");
    File f = document.writeToFile("./" + testDir);

    if(!new File("./" + testDir + "/" + document.getName() + ".html").exists()) {
      fail("File for document does not exist");
    }

    if(f.exists() && f.canRead()) {
      BufferedReader reader = new BufferedReader(new FileReader(f));
      String line;
      String docString = reader.readLine();
      while((line = reader.readLine()) != null) {
        docString += line;
      }
      if (!docString.equals(document.toString().replaceAll("\r\n", "").replaceAll("\n", ""))) {
           fail();
      }
    }

  }




}