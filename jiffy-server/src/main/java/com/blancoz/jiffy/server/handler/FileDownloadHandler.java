package com.blancoz.jiffy.server.handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileDownloadHandler extends RequestHandler {

  private File content;

  public FileDownloadHandler(File f) {
    if(f.exists() && !f.isDirectory()) {
      content = f;
    } else {
      throw new IllegalArgumentException("File must exist and not be a directory");
    }
  }

  @Override
  protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    FileInputStream fis = null;
    OutputStream out = null;

    try {

      fis = new FileInputStream(content);
      response.setContentType("application/octet-stream");

      out = response.getOutputStream();

      byte[] buffer = new byte[20*1024];
      int len;
      while ((len = fis.read(buffer)) > 0) {
        out.write(buffer, 0, len);
      }

    } finally {
      fis.close();
      out.close();

    }
  }

  @Override
  protected void put(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  @Override
  protected void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }

  @Override
  protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
