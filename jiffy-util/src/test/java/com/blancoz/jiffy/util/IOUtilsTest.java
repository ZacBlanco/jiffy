package com.blancoz.jiffy.util;


import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class IOUtilsTest {


  @Test
  public void readWriteFileTest() throws IOException {

    //Write file
    String fileContent = "test-content";
    File f = IOUtils.writeToFile("./target", "myfile.txt", fileContent);
    assertTrue(f.exists() && f.canRead());

    //Make sure we can read our file and the content matches the input exactly.
    String content = IOUtils.readFile(f.getAbsolutePath());
    assertTrue(content.equals(fileContent));

    try{
      IOUtils.writeToFile(null, null, null);
      fail();
    } catch (IOException e) {}

    try{
      IOUtils.writeToFile(null, null, fileContent);
      fail();
    } catch (IOException e) {}

    try{
      IOUtils.writeToFile(null, "aFile.txt", fileContent);
      fail();
    } catch (IOException e) {}

    try{
      IOUtils.writeToFile("goodDir", null, fileContent);
      fail();
    } catch (IOException e) {}

    try{
      IOUtils.writeToFile("goodDir", "goodFile", null);
      fail();
    } catch (IOException e) {}



  }
}
