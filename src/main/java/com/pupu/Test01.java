package com.pupu;

import java.util.Scanner;
import java.util.concurrent.*;

public class Test01 {

    public static void main(String[] args) {
//        System.out.println("hello world!!!!");

//        FutureTask futureTask = new FutureTask(new MyCallable());
//        new Thread(futureTask).start();

        Thread vipThread = new Thread(new MyJoin(), "vipThread");
        Thread vipThread2 = new Thread(new MyJoin2(), "vipThread2");
        vipThread.start();
        vipThread2.start();



        /*循环输出vipThread线程状态*/
        while (vipThread.getState() != Thread.State.TERMINATED) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(vipThread.getState());
//            vipThread.start();
        }

    }


}

class MyCallable implements Callable<String> {

    public String call() throws Exception {
        System.out.println("in");
        return "lalala";
    }

}

class MyJoin implements Runnable {

    public static Object obj = new Object();

    public void run() {

        /*测试waiting状态*/
//        Thread vipThread2 = new Thread(new MyJoin2(),"vipThread2");
//        vipThread2.start();
//        try {
//            vipThread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        synchronized (obj) {
            for (int i = 0; i < 10; i++) {
                /*测试timed_waiting状态*/
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /*测试waiting状态,wait()会释放锁*/
//                if(i==5){
//                    try {
//                        obj.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

                /*join()底层是wait(),释放的是vipThread3的锁*/
//                Thread vipThread3 = new Thread(new MyJoin3(), "vipThread3");
//                vipThread3.start();
//                try {
//                    vipThread3.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class MyJoin2 implements Runnable {

    public void run() {
        /*测试blocked状态*/
        synchronized (MyJoin.obj) {
            for (int i = 0; i < 5; i++) {
                /*测试sleep不会释放锁*/
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class MyJoin3 implements Runnable {

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
