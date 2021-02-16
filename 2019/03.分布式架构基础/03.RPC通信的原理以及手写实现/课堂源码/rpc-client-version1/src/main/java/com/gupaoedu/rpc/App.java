package com.gupaoedu.rpc;

import com.gupaoedu.vip.IHelloService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();

        IHelloService iHelloService = rpcProxyClient.clientProxy
                (IHelloService.class, "localhost", 8080);

        String result = iHelloService.sayHello("Mic");
        System.out.println(result);
    }
}
