package com.xin.netty.keep.alive.message;

public class PongMsg extends BaseMsg {
    public PongMsg() {
        super();
        setType(MsgType.PONG);
    }
}
