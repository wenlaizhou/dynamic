package com.framework.dynamic.boot;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.bootstrap.ServerBootstrapConfig;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.oio.OioEventLoopGroup;
import io.undertow.Undertow;
import io.undertow.util.Headers;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@SpringBootConfiguration
public class Bootstrap {

    public static void main(final String[] args) {
        //Undertow server = Undertow.builder()
        //        .addHttpListener(8080, "localhost")
        //        .setHandler(exchange -> {
        //            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        //            exchange.getResponseSender().send("Hello World");
        //        }).build();
        //server.start();

        SpringApplication app = new SpringApplication(Bootstrap.class);
        app.run();

    }
}
