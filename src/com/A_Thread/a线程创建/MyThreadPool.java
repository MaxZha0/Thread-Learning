package com.A_Thread.a线程创建;

import java.util.concurrent.*;

/**
 *
 * 方式四：使用线程池
 * 背景： 经常创建和销毁、使用量特别大的资源，比如并发情况下的线程，对性能影响很大。
 *  思路： 提前创建好多个线程，放入线程池中，使用时直接获取，使用完
 * 放回池中。可以避免频繁创建销毁、实现重复利用。类似生活中的公共交通工具。
 *  好处：
 *  提高响应速度（减少了创建新线程的时间）
 *  降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 *  便于线程管理
 *  corePoolSize：核心池的大小
 *  maximumPoolSize：最大线程数
 *  keepAliveTime：线程没有任务时最多保持多长时间后会终止
 *  …
 *
 *  Executors：工具类、线程池的工厂类，用于创建并返回不同类型的线程池
 *  Executors.newCachedThreadPool()：创建一个可根据需要创建新线程的线程池
 *  Executors.newFixedThreadPool(n); 创建一个可重用固定线程数的线程池
 *  Executors.newSingleThreadExecutor() ：创建一个只有一个线程的线程池
 *  Executors.newScheduledThreadPool(n)：创建一个线程池，它可安排在给定延迟后运
 * 行命令或者定期地执行。
 * @author Max
 * @date 2021/3/24 16:30
 */
public class MyThreadPool {
    public static void main(String[] args) {
        //1、提供指定线程数量的线程池

//手动设置线程池，设置线程池的属性，一共七种
        ExecutorService service = new ThreadPoolExecutor(2,5,
                1L,TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        //适合于Runnable
        service.execute(new Num2Thread());
        service.execute(new Num3Thread());

        //适合于Callable
//        service.submit();

        //关闭线程池
        service.shutdown();
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
