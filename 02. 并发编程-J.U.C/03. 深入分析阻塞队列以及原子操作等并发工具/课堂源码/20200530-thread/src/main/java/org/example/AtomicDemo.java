package org.example;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 风骚的Mic 老师
 * create-date: 2020/5/30-22:00
 */
public class AtomicDemo {

//    public static int i=0;
    public static AtomicInteger i= new AtomicInteger(0);
    public static void incr(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        i++; //
        i.getAndIncrement();
    }
    public static void main(String[] args) {
        for (int j = 0; j < 1000; j++) {
            new Thread(()->AtomicDemo.incr()).start();
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result:"+i.get());
    }
}
