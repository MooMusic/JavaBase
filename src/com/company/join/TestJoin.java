package com.company.join;

import com.company.SleepTools;

public class TestJoin {

    /**
     * 1、join的用途【插队】
     * 2、线程按照一定的顺序执行.
     */

    private static class Gril implements Runnable{
        private Thread th;
        @Override
        public void run() {
            if (th !=null) {
                try {
                    th.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            SleepTools.second(1); // 模拟业务运行
            System.out.println(Thread.currentThread().getName()+" finished");
        }
    }

    private static class GrilFriend implements Runnable{

        @Override
        public void run() {
            SleepTools.second(1);  // 模拟业务运行
            System.out.println(Thread.currentThread().getName()+" finished");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread girl = new Thread(new Gril());
        girl.setName("girl");

        Thread girlFriend = new Thread(new GrilFriend());
        girlFriend.setName("girlFriend");

        girl.start();
        girlFriend.start();

        girl.join();

        System.out.println(Thread.currentThread().getName()+" finished");

    }


}
