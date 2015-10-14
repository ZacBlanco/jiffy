package com.blancoz.jiffy.server.config;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class JiffyConfigTest {

  static File configFile;

  @BeforeClass
  public static void setup() throws IOException{
    configFile = File.createTempFile("jiffyConfig", "txt");
    BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
    writer.write("#THIS IS A COMMENT LINE\r\n");
    writer.write("jiffy.port 5050\r\n");
    writer.write("#THIS IS another COMMENT LINE\r\n");
    writer.write("jiffy.port.max 20000\r\n");
    writer.close();
  }
  @AfterClass
  public static void cleanup() {
    configFile.delete();
  }

  @Test
  public void configReaderTest() throws IOException, FileNotFoundException {
    JiffyConfig conf = new JiffyConfig();
    conf.readConfigFile(configFile.getAbsolutePath());

    //Make sure port is what we set in the file
    assertTrue(conf.getPort() == 5050);
    try{
      //make sure that we cant set the port over the max we specified.
      conf.setPort(20001);
      fail();
    } catch( IllegalArgumentException e) { }



  }

  @Test
  public void portProtoConfigTest() {
    JiffyConfig conf = new JiffyConfig();
    assertTrue(conf.getPort() == 7070);
    conf.setPort(5000);
    assertTrue(conf.getPort() == 5000);
    try {
      conf.setPort(50000000);
      fail();
    } catch (IllegalArgumentException e) {}


    conf.setProtocol("https");
    assertTrue(conf.getProtocol().equals("https"));
    conf.setProtocol("http");
    assertTrue(conf.getProtocol().equals("http"));
    try {
      conf.setProtocol("bad-protocol");
      fail();
    } catch (IllegalArgumentException e) { }



  }
}
