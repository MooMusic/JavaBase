package com.company.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 *类说明：连接池的实现
 */
public class DBPool {

    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    public DBPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            //TODO
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    // 在mills内无法获取到连接，将会返回null
    public Connection fetchConnection(long mills) throws InterruptedException {
        //TODO
        synchronized (pool){
            if(mills <= 0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                // 可以获取到连接
                return pool.removeFirst();
            }else{
            //等待时刻
            long future=System.currentTimeMillis() + mills;
            //剩余时长
            long remaining=future;
            while (pool.isEmpty() && remaining > 0){
                pool.wait(remaining);
                remaining=future - System.currentTimeMillis();
            }
            Connection conn= null;
            if(!pool.isEmpty()){
                conn= pool.removeFirst();
            }
                return conn;
            }
        }
        }



}
