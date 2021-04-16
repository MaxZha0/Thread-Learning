package com.B_JUC.a_volatile;

/**
 *一、volatile关键字
 *当多个线程进行操作共享数据时，可以保证内存中数据可见
 *  如果不用volatile关键字修饰，分线程修改数据后不会和主线程通信，
 *   用volatile关键字修饰的变量，修改后会和主存通信，时刻更改
 *
 * synchronized加锁也可以，但是效率太低了
 * 相比于sunchronized是一种轻量级的同步特性
 * 但是
 * 1、volatile 不具备 互斥性
 * 2、volatile 不能保证变量的 原子性
 * (多步骤修改内存仍出错)
 *
 * @author Max
 * @date 2021/3/24 22:59
 */
public class Volatile关键字 {
    public static void main(String[] args) throws InterruptedException {
        MyThread r = new MyThread();
        Thread t = new Thread(r);
        t.start();

        while (true){
            if(r.isFlag()){
                System.out.println("Main flag is : "+r.isFlag());
                break;
            }
        }
    }
}


class MyThread implements Runnable{
    //如果不用volatile关键字修饰，分线程修改数据后不会和主线程通信，
    //用volatile关键字修饰的变量，修改后会和主存通信，时刻更改

//    private volatile boolean flag = false;
    private  boolean flag = false;
    @Override
    public void run(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("Flag is "+flag);

    }

    public boolean isFlag() {
        return flag;
    }

}
