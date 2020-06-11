package com.pupu;

import java.util.concurrent.TimeUnit;

public class ProAndComModelTest {

    public static void main(String[] args) {
        Good good = new Good();
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

class Good{
    private int i = 0;
    private Object obj = new Object();

    public void add(){

        synchronized (obj){
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            while(i>0){
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println(Thread.currentThread().getName()+"生产完毕，剩余："+i);
            obj.notifyAll();
        }
    }

    public void take(){


        synchronized (obj){
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            while (i==0){
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i--;
            System.out.println(Thread.currentThread().getName()+"消费完毕，剩余："+i);
            obj.notifyAll();
        }
    }

}
