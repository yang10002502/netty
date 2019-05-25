package com.xin.netty.pojo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ObjectTransferClientHandler extends ChannelInboundHandlerAdapter {


    private final User message = new User();

    public ObjectTransferClientHandler() {
        message.setAge(28);
        message.setName("yangxinlei");
    }

    /**
     * 客户端连接服务器成功触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send the message to Server
        super.channelActive(ctx);
        ctx.writeAndFlush(message);
    }

    /**
     * 服务端返回数据到客户端时触发
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // you can use the Object from Server here
        System.out.println(msg);
        ctx.close();
    }

    /**
     * 有异常时触发
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    /**
     * 与服务器断开连接时触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("the server is off line");
        super.channelInactive(ctx);
    }

    /**
     * 一段时间未进行读写操作时触发
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        ctx.writeAndFlush(message);
        super.userEventTriggered(ctx, evt);
    }
}
