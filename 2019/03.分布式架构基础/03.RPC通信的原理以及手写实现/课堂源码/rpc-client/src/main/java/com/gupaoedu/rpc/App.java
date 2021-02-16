package com.gupaoedu.rpc;

import com.gupaoedu.vip.IHelloService;
import com.gupaoedu.vip.IPaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*RpcProxyClient rpcProxyClient=new RpcProxyClient();

        IHelloService iHelloService=rpcProxyClient.clientProxy
                (IHelloService.class,"localhost",8080);

        String result=iHelloService.sayHello("Mic");
        System.out.println(result);*/

        ApplicationContext context=new
                AnnotationConfigApplicationContext(SpringConfig.class);
        RpcProxyClient rpcProxyClient=context.getBean(RpcProxyClient.class);

        /*IHelloService iHelloService=rpcProxyClient.clientProxy
                (IHelloService.class,"localhost",8080);*/

        IPaymentService iPaymentService=rpcProxyClient.clientProxy(IPaymentService.class,
                "localhost",8080);

        iPaymentService.doPay();
    }
}
