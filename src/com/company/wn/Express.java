package com.company.wn;

/**
 *类说明：快递实体类
 *
 *
 * 1、生产者notifyall、消费者await
 *
 * 等待：消费者
 * syn（对象）{
 *     while（条件不满足）{
 *         对象.wait();   //会释放锁
 *     }
 *     //业务逻辑
 * }
 *
 * 通知：生产者
 * syn（对象）{
 *    //业务逻辑，改变条件
 *    对象.notifyAll(); //不会释放锁
 * }
 *
 *
 */
public class Express {
    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public synchronized void changeKm(){
        this.km=100;
        notifyAll();
    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public  synchronized  void changeSite(){
        this.site = "BeiJing";
        notifyAll();
    }

    public synchronized void waitKm(){
        while (this.km < 99){
            try {
                wait();
                System.out.println("Check km thread["+Thread.currentThread().getId()
                        +"] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getId()
                +"the Km is "+this.km+",I will change db");
    }

    public synchronized void waitSite(){
        while(this.site.equals(CITY)){//快递到达目的地
            try {
                wait();
                System.out.println("Check Site thread["+Thread.currentThread().getId()
                		+"] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getId()
                +"the site is "+this.site+",I will call user");
    }
}
