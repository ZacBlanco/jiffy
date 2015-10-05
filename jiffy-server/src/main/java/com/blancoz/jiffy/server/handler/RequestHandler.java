package com.blancoz.jiffy.server.handler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler extends HttpServlet {

  protected abstract void get(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
  protected abstract void put(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException ;
  protected abstract void post(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException ;
  protected abstract void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException ;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    get(request, response);

  }
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    put(request, response);

  }
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    post(request, response);

  }
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    delete(request, response);
  }

}
