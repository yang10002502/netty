package com.xin.netty.keep.alive.client;

import com.xin.netty.keep.alive.message.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        LoginMsg loginMsg=new LoginMsg();
        loginMsg.setUserName("yangxinlei");
        loginMsg.setPassword("123456");
        ctx.writeAndFlush(loginMsg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.printf("客户端与服务器断开连接");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        if(msg instanceof BaseMsg){
            BaseMsg baseMsg=(BaseMsg) msg;
            switch (baseMsg.getType()){
                case LOGINREPLY:{
                    LoginReplyMsg loginReplyMsg = (LoginReplyMsg) baseMsg;
                    System.out.println(loginReplyMsg.getInfo());
                    RequestMsg requestMsg=new RequestMsg();
                    requestMsg.setRequestBody(new RequestBody("send a request message"));
                    ctx.writeAndFlush(requestMsg);
                    break;
                }
                case PONG:{
                    //收到PING的回复消息PONG，不做任何处理
                    System.out.println("收到服务端回复的PONG消息");
                    break;
                }
                case RESPONSE:{
                    //收到Request回复的Response消息，将结果打印
                    ResponseMsg responseMsg= (ResponseMsg) baseMsg;
                    System.out.println(responseMsg.getResponseBody().getInfo());
                    break;
                }

            }
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        //启动心跳机制，验证服务端在线
        PingMsg pingMsg = new PingMsg();
        ctx.writeAndFlush(pingMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


}
