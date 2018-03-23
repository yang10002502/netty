package com.xin.netty.keep.alive.message;

import java.io.Serializable;

public class RequestBody implements Serializable {
    private String info;

    public RequestBody(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
