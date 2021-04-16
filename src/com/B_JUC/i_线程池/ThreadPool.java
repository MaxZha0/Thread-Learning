package com.B_JUC.i_线程池;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 线程池提供一个线程队列，保存着所有等待状态的线程
 * 避免了频繁的销毁和创建
 *
 * 体系结构
 * java.util.concurrent.Executor 负责线程使用与调度地跟接口
 *   |--ExecutorService 子接口：线程池主要接口
 *       |--ThreadPoolExecutor 线程池实现类
 *       |--ScheduledExecutorService 子接口：负责线程调度
 *           |--ScheduleThreadPoolExecutor 继承了ThreadPoolExecutor，实现了ScheduledExecutorService
 *工具类 Executor
 * Executors.newCachedThreadPool()（无界线程池，可以进行自动线程回收）
 * Executors.newFixedThreadPool(int)（固定大小线程池）
 * Executors.newSingleThreadExecutor()（单个后台线程）
 *
 * ScheduledExecutorService newScheduleThreadPool() 创建固定大小地线程池可以延时或者定时执行任务
 *
 * @author Max
 * @date 2021/3/25 16:09
 */
public class ThreadPool {
    public static void main(String[] args) {
        //第一种方法
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            pool.execute(new Num3Thread());
        }
        //等待所有任务完成关闭
        pool.shutdown();
        //立即关闭
        pool.shutdownNow();

        //第二种方法
        ScheduledExecutorService pool2 = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            pool2.execute(new Num2Thread());
        }
        pool2.shutdown();


    }
}
class Num2Thread implements Runnable{
    @Override
    public void run(){
        for(int i = 0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
class Num3Thread implements Runnable{
    @Override
    public void run(){
        for(int i = 0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+":随便输出");
        }
    }
}
