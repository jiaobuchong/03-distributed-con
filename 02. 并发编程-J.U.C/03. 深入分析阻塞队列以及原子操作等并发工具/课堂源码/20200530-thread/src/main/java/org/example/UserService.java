package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 风骚的Mic 老师
 * create-date: 2020/5/30-20:49
 */
public class UserService {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    ArrayBlockingQueue<User> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

    {
        init();
    }

    public void init() { //不断消费队列的线程
        executorService.execute(() -> {
            while (true) {
                try {
                    User user = arrayBlockingQueue.take(); //阻塞式
                    System.out.println("发送优惠券给：" + user);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean register() {
        User user = new User("Mic");
        addUser(user);
        //发送积分.
        try {
            arrayBlockingQueue.put(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void addUser(User user) {
        System.out.println("添加用户:" + user);
    }

    public static void main(String[] args) {
        new UserService().register();
    }
}
