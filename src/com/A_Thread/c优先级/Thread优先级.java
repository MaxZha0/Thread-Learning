package com.A_Thread.c优先级;

/**
 * 优先级为1-10
 * 不是必然。只是大概率
 * MIN_PRIORITY =1
 * NORM_PRIORITY =5
 * MAX_PRIORITY =10
 * @author Max
 */
public class Thread优先级{

	public static void main(String[] args) {
		Thread t1=new Thread(new RunnableDemo01(),"A");
		Thread t2=new Thread(new RunnableDemo01(),"B");
		Thread t3=new Thread(new RunnableDemo01(),"C");

		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.NORM_PRIORITY);
		t3.setPriority(Thread.MAX_PRIORITY);
		t1.start();
		t2.start();
		t3.start();
		for (int i = 0; i<10; i++) {
			System.out.println("main："+i);
		}
	}
}


class RunnableDemo01 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i<10; i++) {

			System.out.println(Thread.currentThread().getName()+i);
		}
	}
}
