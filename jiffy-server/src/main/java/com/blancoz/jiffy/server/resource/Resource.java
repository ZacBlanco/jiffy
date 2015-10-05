package com.blancoz.jiffy.server.resource;

import com.blancoz.jiffy.server.handler.RequestHandler;

public class Resource {

  private RequestHandler handler;
  private String path;

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
