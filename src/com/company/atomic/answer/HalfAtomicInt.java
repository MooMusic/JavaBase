package com.company.atomic.answer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *类说明：有一个残缺A类实现了线程安全的：
 *get方法和compareAndSet()方法
 *自行实现它的递增方法
 */
public class HalfAtomicInt {
    private AtomicInteger atomicI = new AtomicInteger(0);

    /*请完成这个递增方法*/
    public void increament() {
        for (;;) {
            /**
             * 1、获取旧值
             * 2、当前值与旧值比较，一样更新最新值；不一样再循环
             */
            int i = atomicI.get();
            boolean b = atomicI.compareAndSet(i, i + 1);
            /** 以下这种方式会有问题，atomicI.get()两次调用获取的值不一定一样
             * boolean b = atomicI.compareAndSet(atomicI.get(), atomicI.get()+ 1);
             */

            if (b) {
                break;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        HalfAtomicInt halfAtomicInt = new HalfAtomicInt();
        for(int i=0;i<10;i++){
            new Thread(new MyRun(halfAtomicInt)).start();
        }

        /**
         * 留够充足时间，让子线程执行完任务
         */
        Thread.sleep(5000);
        System.out.println(halfAtomicInt.atomicI.get());
    }

    public static class MyRun implements Runnable{
        private static HalfAtomicInt halfAtomicInt;

        public MyRun(HalfAtomicInt halfAtomicInt) {
            this.halfAtomicInt = halfAtomicInt;
        }

        @Override
        public void run() {
            for(int i=0;i<1000;i++){
                halfAtomicInt.increament();
            }
        }
    }
}
