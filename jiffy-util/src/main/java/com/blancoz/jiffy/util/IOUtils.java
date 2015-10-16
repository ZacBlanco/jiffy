package com.blancoz.jiffy.util;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class IOUtils {


  public static File writeToFile(String directory, String filename, String content) throws IOException {
    if(directory == null || filename == null || content == null) {throw new IOException("parameters must be non-null");}
    File dirs = new File(directory);

    if(!dirs.exists()) {
      if(!dirs.mkdirs()) {
        throw new IOException("Could not create directory structure " + directory);
      }
    }

    File f = new File(dirs.getAbsolutePath() + "/" + filename);

    if(f.exists() && f.canWrite()) {
      return writeFile(f, content);
    } else if (f.createNewFile() && f.canWrite()) {
      return writeFile(f, content);
    } else {
      throw new IOException("Could not write file to location " + directory );
    }
  }

  private static File writeFile(File f, String content) throws IOException {
    FileOutputStream fsout = new FileOutputStream(f);
    fsout.write(content.toString().getBytes());
    fsout.close();
    return f;
  }

  public static String readFile(String file) throws IOException, FileNotFoundException {
    if (!(new File(file).exists())) { throw new FileNotFoundException("Could not find " + file); }
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String content = "";
    String line = "";
    while((line = reader.readLine()) != null) {
      content += line + "\r\n";
    }
    reader.close();
    int c =  content.lastIndexOf("\r\n");
    if( c > 0 ) {
      content = content.substring(0, c);
    }
    return content;
  }

}
