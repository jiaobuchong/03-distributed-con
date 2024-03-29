package com.gupaoedu.vip;

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
public class ServerSocketDemo2 {

    static ExecutorService executorService = Executors.newFixedThreadPool(1000);

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {


            //服务端一定需要去监听一个端口号，ip默认就是本机的ip地址
            //ip:port
            serverSocket = new ServerSocket(8080);

//            while (true) {
            //阻塞(连接阻塞）
//                Socket socket = serverSocket.accept(); //接收客户端的连接（阻塞）
//                executorService.execute(new SocketThread(socket));
//            }

            //阻塞(连接阻塞）能够支持的连接数是有限的
            // 请求过来以后，需要等这个连接处理完工作以后才能去处理其他请求
            // BIO 一个客户端对应一个处理连接
            Socket socket = serverSocket.accept(); //接收客户端的连接（阻塞）
//            executorService.execute(new SocketThread(socket));

            //拿到输入流（阻塞， read/write阻塞）
            BufferedReader in = new BufferedReader(new InputStreamReader
                    (socket.getInputStream()));

            // 输出流，从 server 端响应到客户端
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            //通过控制台拿到数据
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(in.readLine()); //获得客户端的输入信息

            String line = sin.readLine(); //获得控制台输入的数据
            while (!line.equals("bye")) {
                printWriter.println(line); //写回到客户端
                printWriter.flush();
                System.out.println("client:" + in.readLine()); //读取客户端传过来的数据
                // 这里会阻塞
                line = sin.readLine(); // 重新读取控制台的数据
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
