package com.blancoz.jiffy;

import com.blancoz.jiffy.server.JiffyServer;
import com.blancoz.jiffy.server.config.JiffyConfig;
import com.blancoz.jiffy.server.handler.BasicRequestHandler;
import com.blancoz.jiffy.server.handler.RequestHandler;
import com.blancoz.jiffy.server.resource.Resource;

import com.jayway.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;


public class JiffyQuickstartTest {

  static JiffyServer server;
  static JiffyConfig config;
  static int port = 7070;
  static long sysTime;


  @BeforeClass
  public static void setup() throws Exception {
    server = new JiffyServer(port);

    server.addResource(new Resource("/home", new BasicRequestHandler() {
      @Override
      public void handleGet(HttpServletRequest request, HttpServletResponse response) {

      }
    }));

            server.addResource(buildResource("/home"));
    Runnable r = new Runnable() {
      public void run() {
        try {
          server.start();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };

    Thread t = new Thread(r);

    t.start();

  }

  @AfterClass
  public static void tearDown() throws Exception {
    if(server.isStarted()) {
      server.stop();
    }
  }

  public static Resource buildResource(String location) {

    RequestHandler handle = new BasicRequestHandler() {
      @Override
      public void handleGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        sysTime = System.nanoTime();
        response.getWriter().write("System time: " + sysTime );
      }
    };

    Resource r = new Resource(location, handle);
    return r;
  }


  @Test
  public void getSystemTime() {

    String url = "http://localhost:"  + port + "/home";
    String response = RestAssured.given().when().get(url).getBody().asString();
    assertTrue(response.contains("System time:"));
    assertTrue(response.contains("" + sysTime));
  }


}
