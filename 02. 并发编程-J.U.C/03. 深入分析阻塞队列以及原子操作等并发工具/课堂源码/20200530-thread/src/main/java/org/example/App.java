package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
        blockingQueue.put("Mic");
        blockingQueue.take(); //wait/notify   | condition .await/signal | Lock
//        blockingQueue.iterator();
        System.out.println("Hello World!");
    }
}
