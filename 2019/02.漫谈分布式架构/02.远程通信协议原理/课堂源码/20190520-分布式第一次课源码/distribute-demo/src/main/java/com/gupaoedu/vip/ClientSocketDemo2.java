package com.gupaoedu.vip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 通信过程中的阻塞：
 * accept 阻塞（连接阻塞）
 * 流操作的阻塞（read/write 的阻塞）
 *
 * server 和 client 全双工通信
 */
public class ClientSocketDemo2 {

    public static void main(String[] args) {
        try {
            //找到目标的ip和端口
            Socket socket = new Socket("localhost", 8080);

            //在当前链接上写入输入，输出流，会发送到服务端
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 控制台的输入流
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

            // 服务端返回来的输入流
            BufferedReader in = new BufferedReader(new InputStreamReader
                    (socket.getInputStream()));

            String readline = sin.readLine(); //获得控制台的输入
            while (!readline.equals("bye")) {
                // 向服务端写入传输数据
                out.println(readline);
                out.flush();

                // 收到服务端得内容
                System.out.println("Server:" + in.readLine());
                 readline = sin.readLine(); //重新获取
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
