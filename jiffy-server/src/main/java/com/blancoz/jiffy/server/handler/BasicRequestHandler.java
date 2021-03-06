package com.blancoz.jiffy.server.handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public abstract class BasicRequestHandler extends RequestHandler {

  public abstract void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

  @Override
  protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_OK);
    handleGet(request, response);
  }

  @Override
  protected void put(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    return;
  }

  @Override
  protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    return;
  }

  @Override
  protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    return;
  }
}
