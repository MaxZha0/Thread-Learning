package com.B_JUC.e_Condition线程通信;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 接口描述了可能会与锁有关联的条件变量。这些变量在用
 * 法上与使用 Object.wait 访问的隐式监视器类似，但提供了更强大的
 * 功能。需要特别指出的是，单个 Lock 可能与多个 Condition 对象关
 * 联。为了避免兼容性问题， Condition 方法的名称与对应的 Object 版
 * 本中的不同。
 *  在 Condition 对象中，与 wait、 notify 和 notifyAll 方法对应的分别是
 * await、 signal 和 signalAll。
 *  Condition 实例实质上被绑定到一个锁上。要为特定 Lock 实例获得
 * Condition 实例，请使用其 newCondition() 方法。
 *
 * @author Max
 * @date 2021/3/25 14:43
 */
public class ConditionDemo {
    public static void main(String[] args) {
        Window w = new Window();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.start();
        t2.start();
        t3.start();
    }
}

class Window implements Runnable{
    private int sum=40;

    /**
     *  1、实例化公平锁。有先后顺序
     */
    private ReentrantLock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();


    @Override
    public void run() {
        while(true){
            //2、调用lock,用try/finally包裹程序
            lock.lock();
            try {
                condition.signalAll();
                if(sum>0) {
                    System.out.println("窗口号："+Thread.currentThread().getName()+"，序号"+sum--);
                    try {
                        //使得调用如下方法的线程进入阻塞状态，（会释放锁）
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

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

