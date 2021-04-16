package com.B_JUC.g_读写锁;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *ReadWriteLock 维护了一对相关的锁，一个用于只读操作，另一个用于写入操作。
 * 只要没有 writer，读取锁可以由多个 reader 线程同时保持。写入锁是独占的
 *
 * 读写锁
 * 写写操作/互斥
 * 读写操作/互斥
 * 读读操作/无要求
 *
 * @author Max
 * @date 2021/3/25 15:19
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        RWThread r = new RWThread();


        new Thread(new Runnable() {
            @Override
            public void run() {
                r.set((int)(Math.random()*100));
            }
        },"Write").start();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    r.get();
                }
            },"Get").start();
        }


    }

}

class RWThread{
    private int num = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void get(){
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+":"+num);
        }finally {
            lock.readLock().unlock();
        }

    }
    public void set(int num){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName());
            this.num = num;
        }  finally {
            lock.writeLock().unlock();
        }
    }


}
