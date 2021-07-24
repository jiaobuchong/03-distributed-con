package org.example;

import java.util.concurrent.CountDownLatch;

/**
 * 风骚的Mic 老师
 * create-date: 2020/5/30-21:04
 */
public class CountDownLatchDemo1 implements Runnable {

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(new CountDownLatchDemo1()).start();
        }
        //TODO ..
        countDownLatch.countDown(); //1-0
    }

    @Override
    public void run() {
        try {
            countDownLatch.await(); //阻塞线程 | 1000个线程阻塞
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
