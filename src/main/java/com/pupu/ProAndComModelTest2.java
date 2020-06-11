package com.pupu;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProAndComModelTest2 {

    public static void main(String[] args) {
        Good2 good = new Good2();
        new Thread(()->{
            good.add();
        },"生产者1").start();

        new Thread(()->{
            good.add();
        },"生产者2").start();

        new Thread(()->{
            good.add();
        },"生产者3").start();

        new Thread(()->{
            good.take();
        },"消费者1").start();

        new Thread(()->{
            good.take();
        },"消费者2").start();

        new Thread(()->{
            good.take();
        },"消费者3").start();

    }

}

class Good2{
    private int i = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void add(){

        try {
            lock.lock();

            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            while(i>0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println(Thread.currentThread().getName()+"生产完毕，剩余："+i);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void take(){

        try {
            lock.lock();

            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            while(i==0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i--;
            System.out.println(Thread.currentThread().getName()+"消费完毕，剩余："+i);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
