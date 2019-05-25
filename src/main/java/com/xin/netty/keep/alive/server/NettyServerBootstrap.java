package com.xin.netty.keep.alive.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyServerBootstrap {

    private Integer port;

    public NettyServerBootstrap(Integer port) {
        this.port = port;
    }

    private void run() throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new io.netty.bootstrap.ServerBootstrap();
        bootstrap.group(boss, worker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);             //设置连接客户端的个数
        bootstrap.option(ChannelOption.TCP_NODELAY, true);           //禁用nagle算法，确保低延迟
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);     //保持长连接
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline cp = socketChannel.pipeline();
                cp.addLast(new ObjectEncoder());
                cp.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                cp.addLast(new ServerHandler());
            }
        });
        ChannelFuture f = bootstrap.bind(port).sync();
        if (f.isSuccess()) {
            System.out.println("......server start......");
        }
    }

    public static void main(String[] args) throws Exception {
        NettyServerBootstrap serverBootstrap = new NettyServerBootstrap(9999);
        serverBootstrap.run();

    }
}
