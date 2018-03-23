package com.xin.netty.keep.alive.message;

public class PingMsg extends BaseMsg{
    public PingMsg() {
        super();
        setType(MsgType.PING);
    }
}
