package com.blancoz.jiffy.server;


import com.blancoz.jiffy.server.config.JiffyConfig;
import com.blancoz.jiffy.server.handler.BasicErrorHandler;
import com.blancoz.jiffy.server.resource.Resource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;

public class JiffyServer {

  private Server appServer;
  private ServletContextHandler context;

  public JiffyServer (JiffyConfig conf) {
    appServer = setupAppServer(conf);
  }

  public JiffyServer (int port) {
    JiffyConfig conf = new JiffyConfig();
    conf.setPort(port);
    appServer = setupAppServer(conf);
  }

  private Server setupAppServer(JiffyConfig conf) {
    Server server = new Server(conf.getPort());
    context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);
    return server;
  }

  public void addErrorHandler(ErrorHandler errorHandler) {
    if(errorHandler != null) {
      context.addBean(errorHandler);
      appServer.addBean(errorHandler);
      context.setErrorHandler(errorHandler);
    }
  }

  public void start() throws Exception {
    if(!appServer.isStarted()) {
      appServer.start();
      appServer.join();
    }
  }

  public void stop() throws Exception {
    if(appServer.isStopped()) {
      appServer.stop();
    }
  }

  public void restart() throws Exception {
    if(appServer.isStarted()) {
      appServer.stop();
      start();
    } else if(appServer.isStopped()) {
      start();
    }
  }

  public boolean isStarted() {
    return appServer.isStarted();
  }

  public void addResource(Resource resource) {
    context.addServlet(new ServletHolder(resource.getRequestHandler()), resource.getPath());
  }
}
