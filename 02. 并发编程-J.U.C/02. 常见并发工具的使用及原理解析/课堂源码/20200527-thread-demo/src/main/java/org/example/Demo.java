package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 风骚的Mic 老师
 * create-date: 2020/5/27-22:04
 */
public class Demo {

    //MAX_VALUE(Integer)
    static BlockingQueue<String> blockingQueue= new LinkedBlockingDeque<>();

    public static void main(String[] args) {
        /*blockingQueue.add()->如果队列满了，则报错
                / offer会返回添加元素成功的状态/
                put 阻塞式的添加数据*/
    }
}
