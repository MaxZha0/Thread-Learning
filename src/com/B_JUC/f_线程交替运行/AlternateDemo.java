package com.B_JUC.f_线程交替运行;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  Condition高级应用
 *
 * 三个线程有顺序地输出ABC ABC
 *
 * @author Max
 * @date 2021/3/25 14:53
 */
public class AlternateDemo {
    public static void main(String[] args) {
        AlterThread a = new AlterThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    a.loopA(i);
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    a.loopB(i);
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    a.loopC(i);
                }
            }
        },"C").start();

    }
}
class AlterThread {
    private int num = 1;

    private ReentrantLock lock = new ReentrantLock(true);
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA(int total) {
        lock.lock();
        try {
            if(num != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+" "+total);

            num = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB(int total) {
        lock.lock();
        try {
            if(num != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+" "+total);

            num = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(int total) {
        lock.lock();
        try {
            if(num != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+" "+total);
            System.out.println();

            num = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
