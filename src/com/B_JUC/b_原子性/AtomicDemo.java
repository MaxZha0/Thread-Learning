package com.B_JUC.b_原子性;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *i++原子性问题,i++操作实际上分为三个步骤“读-改-写”
 *      int i =10;
 *      i = i++; //10
 *
 *      实际为
 *      int t = i;
 *      i = i + 1;
 *      i = t;
 * 原子变量：
 * java.util.concurrent.atomic包中定义了很多原子变量
 *      1、封装的变量都用volatile修饰，保证内存可见性
 *      2、CAS（Compare-And-Swap） 算法保证数据原子性
 *          CAS算法是硬件对于并发操作共享数据的支持
 *          包含了三个操作数：
 *          内存值 V
 *          预估值 A
 *          更新值 B
 *          当且仅当 V==A 时，V = B，否则不做任何操作
 *输出：普通变量会出现线程安全问题，出现重复数据
 *     原子变量不会
 *
 * @author Max
 * @date 2021/3/24 23:45
 */
public class AtomicDemo {
    public static void main(String[] args) {
        int j = 10;
        j = j++;
        //10
        System.out.println(j);

        atomicThread a = new atomicThread();
        for(int i = 0;i < 10;i++){
            new Thread(a).start();
        }

    }
}

class atomicThread implements Runnable{
    //普通变量
    private int num = 0;
    //原子变量
    private AtomicInteger aNum = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"普通变量:    "+getNum());
        System.out.println(Thread.currentThread().getName()+"原子变量:"+getANum());
    }

    public int getNum() {
        return num++;
    }
    public int getANum() {
        //相当于i++
        return aNum.getAndIncrement();
    }
}
