package com.xin.netty.keep.alive.server;

import com.xin.netty.keep.alive.message.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        if(msg instanceof BaseMsg){
            BaseMsg baseMsg=(BaseMsg) msg;
            switch (baseMsg.getType()){
                case LOGIN:{
                    LoginMsg loginMsg = (LoginMsg) baseMsg;
                    System.out.println(loginMsg);
                    LoginReplyMsg loginReplyMsg=new LoginReplyMsg();
                    loginReplyMsg.setInfo("登陆成功");
                    ctx.writeAndFlush(loginReplyMsg);
                    break;
                }
                case PING:{
                    System.out.println("收到PING消息");
                    PongMsg pongMsg = new PongMsg();
                    ctx.writeAndFlush(pongMsg);
                    break;
                }
                case REQUEST:{
                    RequestMsg requestMsg = (RequestMsg) baseMsg;
                    System.out.println("收到客户端的请求消息："+requestMsg.getRequestBody().getInfo());
                    ResponseMsg responseMsg = new ResponseMsg();
                    responseMsg.setResponseBody(new ResponseBody("收到你得请求，正在处理中，请稍后。。。"));
                    ctx.writeAndFlush(responseMsg);
                    break;
                }
            }
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
