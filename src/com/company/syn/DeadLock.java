package com.company.syn;

import com.company.SleepTools;

public class DeadLock {
    public static Object lock1=new Object();
    public static Object lock2=new Object();

    public static class MyThread extends Thread{
        @Override
        public void run() {
            /**
             * 1、先获取第一把锁
             * 2、然后获取第二把锁
             */
            synchronized(lock1){
                SleepTools.second(1);
                synchronized (lock2){
                    System.out.println("MyThread");
                }
            }

        }
    }

    public static class MyRun implements Runnable{
        @Override
        public void run() {
            /**
             * 1、先获取第二把锁
             * 2、然后获取第一把锁
             */
            synchronized(lock2){
                SleepTools.second(1);
                synchronized (lock1){
                    System.out.println("MyRun");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new MyThread()).start();
        new Thread(new Thread(new MyRun())).start();

        System.out.println(Thread.currentThread().getName() + ": main");
    }
}
