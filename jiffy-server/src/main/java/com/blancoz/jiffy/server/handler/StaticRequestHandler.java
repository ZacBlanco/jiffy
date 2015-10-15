package com.blancoz.jiffy.server.handler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.blancoz.jiffy.web.html.HtmlDocument;

public class StaticRequestHandler extends RequestHandler {

  private HtmlDocument document;

  public StaticRequestHandler(HtmlDocument document) {
    this.document = document;
  }

  public void setDocument(HtmlDocument d) {
    if(d != null) {
      this.document = d;
    }
  }

  public HtmlDocument getDocument() {
    return this.document;
  }
  @Override
  protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().write(document.toString());
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
