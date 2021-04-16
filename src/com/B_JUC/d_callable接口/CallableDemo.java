package com.B_JUC.d_callable接口;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的第三种方法，实现Callable借口
 * 1、创建一个实现callable接口的实现类
 * 2、实现类中的call方法
 * 3、创建callable接口实现类的对象
 * 4、将此对象作为参数传给FutureTask构造器，创建对象
 * 5、新对象传给Thread，创建线程对象
 * 6、start
 *
 *与使用Runnable相比， Callable功能更强大些
 *  相比run()方法，可以有返回值
 *  方法可以抛出异常
 *  支持泛型的返回值
 *  需要借助FutureTask类，比如获取返回结果
 *
 * @author Max
 * @date 2021/3/25 12:42
 */
public class CallableDemo {
    public static void main(String[] args) {
        NumThread n = new NumThread();
        //需要FutureTask实现类支持，用于接受运算结果
        FutureTask f = new FutureTask(n);
        Thread t = new Thread(f);
        t.start();

        try {
            //get返回值为FutureTask构造器参数callable实现类重写的call方法返回值
            //线程执行完才能得到返回值
            Object sum = f.get();
            System.out.println("线程返回值为 "+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class NumThread implements Callable {

    @Override
    public Object call() throws Exception {
        int num = 0;
        for(int i = 0;i <= 10;i ++){
            num += i;
            System.out.println("线程内output:"+num);
        }
        return num;
    }
}
