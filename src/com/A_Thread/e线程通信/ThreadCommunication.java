package com.A_Thread.e线程通信;

/**
 * 线程通信，两个线程交替报数
 * wait()
 * notify()
 * notifyAll()
 * 这三个方法只有在synchronized方法或synchronized代码块中才能使用，
 * 调用者必须是同步代码块或同步方法中的同步监视器
 * @author Max
 * @date 2021/3/24 15:50
 */
public class ThreadCommunication {
    public static void main(String[] args) {
        Number n = new Number();
        Thread t1 = new Thread(n);
        Thread t2 = new Thread(n);
        Thread t3 = new Thread(n);
        t1.start();
        t2.start();
        t3.start();

    }
}
class Number implements Runnable{
    private int sum=40;

    @Override
    public void run() {
        while(true){
            synchronized (this) {
                //唤醒优先级高的线程
                notify();

                if(sum>0) {
                    System.out.println("窗口号："+Thread.currentThread().getName()+"，序号"+sum--);
                    try {
                        //使得调用如下方法的线程进入阻塞状态，（会释放锁）
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    break;
                }
            }
        }
    }
}
