package com.xin.netty.keep.alive.message;

public class ResponseMsg extends BaseMsg {
    private ResponseBody responseBody;

    public ResponseMsg() {
        super();
        setType(MsgType.RESPONSE);
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }
}
