package com.gupaoedu.vip.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Socket 是一个全双工的协议
 */
public class ClientSocketDemo1 {

    public static void main(String[] args) {
        try {
            //找到目标的ip和端口
            Socket socket = new Socket("localhost", 8080);

            //在当前链接上写入输入
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello 2021");

            // 服务端返回来的输入流
            BufferedReader in = new BufferedReader(new InputStreamReader
                    (socket.getInputStream()));
            System.out.println("from server: " + in.readLine());

            out.close();
            in.close();
            socket.close();




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
