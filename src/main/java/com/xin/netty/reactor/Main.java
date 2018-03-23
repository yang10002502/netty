package com.xin.netty.reactor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Reactor reactor = new Reactor();
        new Handler(reactor.selector);
        reactor.run();
    }
}