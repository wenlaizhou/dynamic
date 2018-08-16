package com.framework.dynamic.boot;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.bootstrap.ServerBootstrapConfig;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.undertow.Undertow;
import io.undertow.util.Headers;

public class Bootstrap {

    public static void main(final String[] args) {
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(exchange -> {
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send("Hello World");
                }).build();
        server.start();

        ServerBootstrap bs = new ServerBootstrap();
        EpollEventLoopGroup parent = new EpollEventLoopGroup(2);

        bs.group(null, null);
        System.out.println(bs.config().toString());
    }
}
