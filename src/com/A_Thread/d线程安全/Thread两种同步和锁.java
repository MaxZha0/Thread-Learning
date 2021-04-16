package com.A_Thread.d线程安全;

/**
 * 1. 多线程出现了安全问题
 * 2. 问题的原因：
 * 当多条语句在操作同一个线程共享数据时，一个线程对多条语句只执行了一部分，还没有执行完，另一个线程参与进来执行。
 * 导致共享数据的错误。
 * 3. 解决办法：
 * 对多条操作共享数据的语句，只能让一个线程都执行完，在执行过程中，其他线程不可以参与执行。
 *
 * 方法一：同步代码块
 * synchronized（同步监视器）{
 *     同步代码
 * }
 * 说明：1、操作共享数据的代码为被同步代码
 * 		2、同步监视器，俗称：锁。任何一个类的对象都可以当锁
 * 			要求：多个线程必须公用一把锁，随便一个都可以
 * 			实现接口方法用同步代码块要可以直接 this 锁
 * 			继承Thread方法用同步代码块要 static object 锁
 *
 * 方法二：同步方法
 * 可以把操作共享数据得代码完整的放在一个方法中，并声明同步的
 * 		实现接口方法用同步方法也需要锁 this，但不需要显式声明锁
 * 		继承Thread方法用同步方法需要声明同步方法static，锁是当前类本身
 *
 *
 */
public class Thread两种同步和锁 {

	public static void main(String[] args) {
		RunnableDemo06 r1=new RunnableDemo06();
		RunnableDemo07 r2=new RunnableDemo07();

		Thread t1=new Thread(r1);
		Thread t2=new Thread(r1);
		Thread t3=new Thread(r2);
		Thread t4=new Thread(r2);
		t1.setName("1");
		t2.setName("2");
		t3.setName("3");
		t4.setName("4");

		t1.start();
		t2.start();

		t3.start();
		t4.start();

	}

}


/**
 * 同步代码块 方法
 */
class RunnableDemo06 implements Runnable{
	private int sum=40;

	@Override
	public void run() {
		while(true){
			//同步代码块
			synchronized (this) {
				if(sum>0) {
					System.out.println("窗口号："+Thread.currentThread().getName()+"，序号"+sum--);
				}else {
					break;
				}
			}
		}
	}
}

/**
 * 同步方法
 */
class RunnableDemo07 implements Runnable{
	private int sum=40;

	@Override
	public void run() {
		while(true){
			show();
		}
	}

	private synchronized void show(){
		if(sum>0) {
			System.out.println("窗口号："+Thread.currentThread().getName()+"，序号"+sum--);
		}
	}
}

