package com.framework.dynamic.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
public class Bootstrap {

    public static void main(final String[] args) throws Exception {
        //Undertow server = Undertow.builder()
        //        .addHttpListener(8080, "localhost")
        //        .setHandler(exchange -> {
        //            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        //            exchange.getResponseSender().send("Hello World");
        //        }).build();
        //server.start();
        //GroovyScriptEngine engine = new GroovyScriptEngine();
        //engine.getGroovyClassLoader().parseClass(
        //engine.run("", "");
        //new SpringApplicationBuilder(Bootstrap.class).web(WebApplicationType.SERVLET).build().run(args);

        Logger log = LoggerFactory.getLogger("root");
        for (int i = 0; i < 200; i++) {
            log.error("hello world");
        }
    }
}
