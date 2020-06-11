package com.pupu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProAndComModelTest3 {

    public static void main(String[] args) {
        Good3 good = new Good3();
        new Thread(() -> {
            good.add();
        }, "生产者1").start();

        new Thread(() -> {
            good.add();
        }, "生产者2").start();

        new Thread(() -> {
            good.add();
        }, "生产者3").start();

        new Thread(() -> {
            good.take();
        }, "消费者1").start();

        new Thread(() -> {
            good.take();
        }, "消费者2").start();

        new Thread(() -> {
            good.take();
        }, "消费者3").start();

    }

}

class Good3 {
    private int i = 0;
    private BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

    public void add() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
        blockingQueue.add(i);
        System.out.println(Thread.currentThread().getName() + "生产出：" + i);
    }

    public void take() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int m = 0;
        try {
            m = (int)blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "消费了：" + m);
    }

}
