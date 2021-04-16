package com.B_JUC.j_Fork_Join_工作窃取;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 *  采用 “工作窃取”模式（work-stealing）：
 * 当执行新的任务时它可以将其拆分分成更小的任务执行，并将小任务加到线程队列中，
 * 然后再从一个随机线程的队列中偷一个并把它放在自己的队列中。
 *
 * 模拟一个递归算法，没啥卵用
 * @author Max
 * @date 2021/3/25 16:38
 */
public class ForkJoinDemo {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSum(0L,10000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
    }
}

class ForkJoinSum extends RecursiveTask<Long>{
    // private  static final long serialVersionUID =
    private long start;
    private long end;
    //临界值
    private static final long THURSHOLD = 10000L;

    public ForkJoinSum(long start,long end){
        this.end = end;
        this.start = start;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if(length <= THURSHOLD){
            long sum = 0L;
            for (long i = start; i <= end ; i++) {
                sum += i;
            }
            return sum;
        }else {
            long mid = (start + end)/2;
            ForkJoinSum left = new ForkJoinSum(start,mid);
            left.fork();
            ForkJoinSum right = new ForkJoinSum(mid+1,end);
            right.fork();
            return  left.join() + right.join();
        }
    }
}
