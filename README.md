# jiffy
A dead simple framework that wraps up the open source Jetty application server to make developing beautiful web applications easy

Create simply beautiful web applications with only a few lines of code. Written in pure Java.

The Jiffy framework builds a small abstraction layer on top of Jetty servlets to make starting a web server dead easy. With this framework, it is possible to get a server running in under 60 seconds.

## Quickstart

Getting a jiffy server up and running in less than 60 seconds


```java

JiffyServer app = new JiffyServer(7070);

app.addResource(new Resource("/home", new BasicRequestHandler() {
      @Override
      protected void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        res.getWriter().write("Hello World!");
      }
    }));
    
app.start();

```

Simple as that! Start serving your webpages with under 10 lines of code!

Jiffy makes it easy to add and manipulate content on your webpage with simple handler methods that are called every time someone  requests a resource from your server.

## Building Jiffy From Source

I'm going to assume that you've already installed the latest JDK and JRE. Current Jiffy is built with Java 8.

The Jiffy project and dependency structure is based on [Maven](http://maven.apache.org).

To build this project you'll likely need to install maven. If you haven't already here's a guide to getting maven on your system:

- [Windows](https://maven.apache.org/guides/getting-started/windows-prerequisites.html)
- [OS X/ Linux](https://maven.apache.org/install.html)

To build linux, `cd` into the root directory of the project and run the following command in your command prompt or terminal:

```
mvn clean install
```

This command will download the dependencies, run all the unit tests and build the project from source.

*Note!* jars created when built from source are found in `{module-name}/target`

## Using Jiffy in Your Application

To use Jiffy in your application you can use the JAR files generated from the command `mvn clean install` which will given you the latest development version of Jiffy.

If you want the latest stable release, check the releases folder for the most recent stable version builds. You can include those jars as libraries in your own project.


