package com.blancoz.jiffy.server.handler;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ErrorHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public abstract class BasicErrorHandler extends ErrorHandler {

  @Override
  public abstract void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException;

}
