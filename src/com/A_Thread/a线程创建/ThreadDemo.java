package com.A_Thread.a线程创建;

public class ThreadDemo {

	public static void main(String[] args) {
		MyThread t1=new MyThread("A");
		MyThread t2=new MyThread("B");
		//线程的启动不能用run执行，需要用start
		//不会并发运行
//		t1.run();
//		t2.run();
		//1、启动线程
		//2、调用线程run（）方法
		t1.start();
		t2.start();


		MyRunnable r1=new MyRunnable("AA");
		MyRunnable r2=new MyRunnable("BB");
		Thread t11=new Thread(r1);
		Thread t12=new Thread(r2);
		t11.start();
		t12.start();

	}
}
