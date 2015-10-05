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
      protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        res.getWriter().write("Hello World!");
      }
    }));
    
app.start();

```

Simple as that! Start serving your webpages with under 10 lines of code!

Jiffy makes it easy to add and manipulate content on your webpage with simple handler methods that are called every time someone  requests a resource from your server.