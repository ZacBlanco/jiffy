package com.blancoz.jiffy.web.css;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class CssDocumentTest {

    @Test
    public void basicCssDocumentTest() throws Exception {
        CssDocument document = new CssDocument("test-css");
        CssSelector test = new CssSelector("a > .b");
        test.addProperty("background-color", "#123456");
        assertTrue(document.getName().equals("test-css"));
        try {
            document.getSelector("nonexistant");
            fail();
        } catch (NoSuchElementException e) { }

        document.addSelector(test);
        assertTrue(document.getSelector(test.getSelector()) == test);
        String doc = document.toString();
        assertTrue(doc.contains("a > .b"));
        assertTrue(doc.contains("{"));
        assertTrue(doc.contains("}"));
        assertTrue(doc.contains("background-color:#123456"));



        document.removeSelector(test.getSelector());
        try {
            document.getSelector(test.getSelector());
            fail();
        } catch (NoSuchElementException e) { }
    }
}
