package com.blancoz.jiffy.web.css;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CssSelectorTest {

    @Test
    public void basicCssSelectorTest() throws Exception {

        String selStr = "a > b .myclass";
        CssSelector selector = new CssSelector(selStr);

        assertTrue(!selector.hasProperty("background-color"));
        assertTrue(!selector.hasPropertyValue("MYPROPVALUE"));
        assertTrue(selector.getSelector() != null);
        assertTrue(selector.getSelector().equals(selStr));

        selector.addProperty("background-color", "#123456");
        try {
            selector.setSelector(null);
            fail();
        }
        catch (NullPointerException e) {}

        assertTrue(selector.hasPropertyValue("#123456") == true);
        assertTrue(selector.hasProperty("background-color") == true);
        assertTrue(selector.toString().contains("background-color:#123456"));
        assertTrue(selector.toString().contains(selStr + " {"));

        selector.setSelector("my-new-selector");
        assertTrue(selector.getSelector().equals("my-new-selector"));
    }

}
