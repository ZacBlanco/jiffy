package com.blancoz.jiffy.server;


import com.blancoz.jiffy.server.config.JiffyConfig;
import com.blancoz.jiffy.server.resource.Resource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JiffyServer {

  private Server appServer;
  private ServletContextHandler context;

  public JiffyServer (JiffyConfig conf) {
    appServer = setupAppServer(conf);
  }

  private Server setupAppServer(JiffyConfig conf) {
    Server server = new Server(conf.getPort());
    context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);
    return server;
  }

  public void start() throws Exception {
    if(!appServer.isStarted()) {
      appServer.start();
      appServer.dumpStdErr();
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
