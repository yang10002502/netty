package com.xin.netty.keep.alive.message;

import java.io.Serializable;

public class ResponseBody implements Serializable {
    private String info;

    public ResponseBody(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
