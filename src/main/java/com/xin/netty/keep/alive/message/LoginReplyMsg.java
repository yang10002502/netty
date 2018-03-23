package com.xin.netty.keep.alive.message;

public class LoginReplyMsg extends BaseMsg {

    private Integer status;
    private String info;

    public LoginReplyMsg() {
        super();
        setType(MsgType.LOGINREPLY);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
