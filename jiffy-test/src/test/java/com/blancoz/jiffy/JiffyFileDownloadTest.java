package com.blancoz.jiffy;


import com.blancoz.jiffy.server.JiffyServer;
import com.blancoz.jiffy.server.handler.*;
import com.blancoz.jiffy.server.resource.Resource;
import org.eclipse.jetty.server.Request;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class JiffyFileDownloadTest {

  static JiffyServer server;
  static int port = 7070;

  @BeforeClass
  public static void main(String[] args) throws Exception {
    server = new JiffyServer(port);

    server.addResource(new Resource("/home", new BasicRequestHandler() {
      @Override
      public void handleGet(HttpServletRequest request, HttpServletResponse response) {

      }
    }));

//    BasicErrorHandler errorHandler = new BasicErrorHandler() {
//      @Override
//      public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.getWriter().write(Err404);
//      }
//    };
//    server.addErrorHandler(errorHandler);

    String path = "/Users/zblanco/Documents/Boxes/Sandbox_HDP_2.3_1_virtualbox (1).ova";

    Resource box = fileResource(path, "/hadoop-sandbox.ova");
    server.addResource(box);
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

  @Rule
  public TestWatcher watchman = new TestWatcher() {
    @Override
    protected void failed(Throwable e, Description description) {
      // take screenshot, etc.
      try {
        tearDown();
      } catch (Exception ex) {
        System.out.println("Could not shut down server.");
      }
    }
  };


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
        long sysTime = System.nanoTime();
        response.getWriter().write("System time: " + sysTime);
      }
    };

    Resource r = new Resource(location, handle);
    return r;

  }

  public static Resource fileResource(String fileLocation, String location) {
    File f = new File(fileLocation);

    FileDownloadHandler handle = new FileDownloadHandler(f);

    Resource r = new Resource(location, handle);
    return r;
  }
}
