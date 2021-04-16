package com.A_Thread.d线程安全;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁-JDK5.0新增
 *synchronized 与 Lock 的对比
 * 相同点：
 * 都可以解决线程安全问题
 * 不同点：
 * 1. Lock是显式锁（手动开启和关闭锁，别忘记关闭锁）， synchronized是隐式锁，出了作用域自动释放同步监视器
 * 2. Lock只有代码块锁， synchronized有代码块锁和方法锁
 * 3. 使用Lock锁， JVM将花费较少的时间来调度线程，性能更好。并且具有更好的扩展性（提供更多的子类）
 * 优先使用顺序：
 * Lock=> 同步代码块（已经进入了方法体，分配了相应资源）=>同步方法（在方法体之外）
 *
 * @author Max
 * @date 2021/3/24 14:54
 */
public class Thread第三种方式lock {
    public static void main(String[] args) {
        Window2 w = new Window2();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.start();
        t2.start();
        t3.start();

    }
}
class Window2 implements Runnable{
    private int sum=40;

    /**
     *  1、实例化公平锁。有先后顺序
     */
    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while(true){
            //2、调用lock,用try/finally包裹程序
            lock.lock();
            try {
                if(sum>0) {
                    System.out.println("窗口号："+Thread.currentThread().getName()+"，序号"+sum--);
                }else {
                    break;
                }
            } finally {
                //3、解锁
                lock.unlock();
            }

        }
    }
}
