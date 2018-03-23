package com.xin.netty.keep.alive.message;

public class RequestMsg extends BaseMsg {
    private RequestBody requestBody;

    public RequestMsg() {
        super();
        setType(MsgType.REQUEST);
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }
}
