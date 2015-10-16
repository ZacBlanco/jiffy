package com.blancoz.jiffy.web.js;

import com.blancoz.jiffy.util.IOUtils;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class JavascriptDocumentTest {

  @Test
  public void basicJavascriptTest() throws Exception {
        String scriptContent = "blah blah blah;";

    File script = IOUtils.writeToFile("./target", "testScript.js", scriptContent);

    JavaScriptDocument test = new JavaScriptDocument(script);

    assertTrue(test.getFileName().equals("testScript.js"));
    assertTrue(test.getName().equals("testScript"));
    assertTrue(test.toString().equals(scriptContent));
    test.setName("newName");
    assertTrue(test.getName().equals("newName"));
    assertTrue(test.getFileName().equals("newName.js"));

    try {
      JavaScriptDocument badDoc = new JavaScriptDocument(null);
      fail();
    } catch (IllegalArgumentException e) {}
    try {
      JavaScriptDocument badDoc = new JavaScriptDocument(script);
      badDoc.setName(null);
      fail();
    } catch (IllegalArgumentException e) {}




  }
}
