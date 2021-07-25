package com.gupaoedu.vip.netty.rpc.registry;

import com.gupaoedu.vip.netty.rpc.protocol.InvokerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RegistryHandler extends ChannelInboundHandlerAdapter {

    //用保存所有可用的服务
    public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<String, Object>();

    //保存所有相关的服务类
    private List<String> classNames = new ArrayList<String>();

    public RegistryHandler() {
        //完成递归扫描
        // 1. 根据包名将所有符合条件的 class 全部扫描出来，放到一个容器中（如果是分布式，读取配置文件）
        // 2. 给每一个对应的classq起一个唯一的名字，作为服务名称，保存到一个容器中
        // 4. 要去注册好容器中找到符合条件的服务
        // 5. 通过远程调用 Provider 得到返回结果，返回给客户端
        scannerClass("com.gupaoedu.vip.netty.rpc.provider");
        doRegister();
    }


    // 有客户端连接上的时候会回调
    // 3. 当有客户端连接过来以后，就会获取协议内容 InvokerProtocol 对象
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        InvokerProtocol request = (InvokerProtocol) msg;

        //当客户端建立连接时，需要从自定义协议中获取信息，拿到具体的服务和实参
        //使用反射调用
        if (registryMap.containsKey(request.getClassName())) {
            Object clazz = registryMap.get(request.getClassName());
            Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParames());
            result = method.invoke(clazz, request.getValues());
        }
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    // 连接发生异常时会回调
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    /*
     * 递归扫描，正常来说应该读取配置文件
     */
    private void scannerClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            //如果是一个文件夹，继续递归
            if (file.isDirectory()) {
                scannerClass(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    /**
     * 完成注册
     */
    private void doRegister() {
        if (classNames.size() == 0) {
            return;
        }
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                Class<?> i = clazz.getInterfaces()[0];
                // 应该从配置文件读取
                // 调用的时候才去解析，用反射
                registryMap.put(i.getName(), clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
