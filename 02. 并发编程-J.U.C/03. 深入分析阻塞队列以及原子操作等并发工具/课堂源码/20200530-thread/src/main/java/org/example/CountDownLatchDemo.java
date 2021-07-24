package org.example;

import java.util.concurrent.CountDownLatch;

/**
 * 风骚的Mic 老师
 * create-date: 2020/5/30-21:04
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "->begin");
            countDownLatch.countDown(); //初始值-1 =3-1=2;
            System.out.println(Thread.currentThread().getName() + "->end");
        }, "t1").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "->begin");
            countDownLatch.countDown(); //2-1=1;
            System.out.println(Thread.currentThread().getName() + "->end");
        }, "t2").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "->begin");
            countDownLatch.countDown(); //1-1=1;
            System.out.println(Thread.currentThread().getName() + "->end");
        }, "t3").start();

        countDownLatch.await(); //阻塞Main线程
    }
}
