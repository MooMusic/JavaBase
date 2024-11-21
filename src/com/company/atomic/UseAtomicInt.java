package com.company.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *类说明：演示基本类型的原子操作类
 */
public class UseAtomicInt {
    static AtomicInteger ai;

    public static void main(String[] args) {
        ai = new AtomicInteger(0);
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.incrementAndGet());
        System.out.println(ai.addAndGet(24));

        //ai.compareAndSet();

    }
}
