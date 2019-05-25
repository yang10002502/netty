package com.xin.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private final ByteBuf firstMessage;

    /**
     * Creates a client-side handler.
     */
    public EchoClientHandler() {
        byte[] message = "hello,i miss you".getBytes();
        firstMessage = Unpooled.buffer(message.length);
        firstMessage.writeBytes(message);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Connected");
        //ctx.writeAndFlush(Unpooled.copiedBuffer("This msg from clien!", CharsetUtil.UTF_8));
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Msg Form Server: " + in.toString(CharsetUtil.UTF_8));
        //ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
