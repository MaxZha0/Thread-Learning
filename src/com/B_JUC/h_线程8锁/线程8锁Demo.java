package com.B_JUC.h_线程8锁;

/**
 *
 * 题目：判断打印“one”or“two”
 * 1、两个普通synchronized方法，两个线程，标准打印？//one two
 * 2、给getOne加入Thread.sleep(3000);，打印？//3s one two
 * 3、新加普通方法getThree（） 三个线程打印？ //three 3s one two
 * 4、两个普通同步方法，两个num对象，打印？//two 3s one
 * 5、在1上修改getone()为静态同步方法，两个线程打印？//two 3s one
 * 6、在1上修改两个同步方法都为static，两个线程打印？//3s one two
 * 7、一个静态同步一个普通同步，两个对象打印？ //two 3s one
 * 8、两个静态同步，两个对象，打印？//3s one two
 *
 *关键点：
 * 1、非静态方法地锁为 this
 * 2、静态方法地锁为对应class类
 * 3、某一个时刻内只能有一个线程有锁
 *
 *
 * @author Max
 * @date 2021/3/25 15:39
 */
public class 线程8锁Demo {
    public static void main(String[] args) {
        Number n = new Number();
        Number n2 = new Number();
        new Thread(new Runnable() {
            @Override
            public void run() {
               n.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
               n2.getTwo();
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//               n.getThree();
//            }
//        }).start();
    }
}
class Number{
    public static synchronized void getOne()  {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }
    public static synchronized void getTwo(){
        System.out.println("two");
    }

    public void getThree(){
        System.out.println("three");
    }
}


