package com.gupaoedu.vip;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
@RpcService(value = IHelloService.class, version = "v1.0")
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String content) {
        System.out.println("【V1.0】request in sayHello:" + content);
        return "【V1.0】Say Hello:" + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("【V1.0】request in saveUser:" + user);
        return "【V1.0】SUCCESS";
    }
}
