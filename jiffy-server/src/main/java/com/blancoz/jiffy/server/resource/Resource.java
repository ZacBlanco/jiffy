package com.blancoz.jiffy.server.resource;

import com.blancoz.jiffy.server.handler.RequestHandler;
import org.eclipse.jetty.server.handler.ErrorHandler;

public class Resource {

  private RequestHandler handler;
  private String path;
  private ErrorHandler errorHandler;


  public Resource(String path, RequestHandler handler) {
    this.path = path;
    this.handler = handler;
  }

  public RequestHandler getRequestHandler() {
    return handler;
  }

  public String getPath() {
    return path;
  }

}
