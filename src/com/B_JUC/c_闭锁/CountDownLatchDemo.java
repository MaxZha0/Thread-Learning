package com.B_JUC.c_闭锁;

import java.util.concurrent.CountDownLatch;

/**
 *
 * coundownlatch 闭锁，在完成某些运算时，只有其他所有线程运算全部完成，当前运算才继续进行
 * 1、线程实现类中用try/finally包裹代码，latch.countDown();闭锁计数-1
 * 2、主线程中生成CountDownLatch对象，记闭锁总数
 * 3、把该对象作为构造参数生成多个线程对象
 * 4、latch.await();方法会等待所有线程运行完之后才继续
 *
 * @author Max
 * @date 2021/3/25 11:48
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(5);
        LatchDemo ld = new LatchDemo(latch);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(ld).start();
        }
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("耗时 ："+(end-start));

    }

}

class LatchDemo implements Runnable{
    private CountDownLatch latch;
    public LatchDemo(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        try {
            for(int i = 0;i < 10;i++){
                System.out.println(i);
            }
        } finally {
            latch.countDown();
        }
    }
}
