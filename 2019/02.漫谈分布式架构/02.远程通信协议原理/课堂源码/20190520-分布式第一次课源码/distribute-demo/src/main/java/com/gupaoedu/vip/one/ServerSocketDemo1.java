package com.gupaoedu.vip.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class ServerSocketDemo1 {

    static ExecutorService executorService = Executors.newFixedThreadPool(1000);

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {


            //服务端一定需要去监听一个端口号，ip默认就是本机的ip地址
            //ip:port
            serverSocket = new ServerSocket(8080);
            while (true) {

                //阻塞(连接阻塞）
                Socket socket = serverSocket.accept(); //接收客户端的连接（阻塞）



                //  拿到输入流（阻塞， read/write阻塞）
                // 从客户端发过来的请求流
                BufferedReader in = new BufferedReader(new InputStreamReader
                        (socket.getInputStream()));
                // 获得客户端的输入信息
                System.out.println("Client:" + in.readLine());

                TimeUnit.SECONDS.sleep(10);
            }




        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
