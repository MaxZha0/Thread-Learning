package com.A_Thread.a线程创建;
/**
 * 1) 定义子类，实现Runnable接口。
 * 2) 子类中重写Runnable接口中的run方法。
 * 3) 通过Thread类含参构造器创建线程对象。
 * 4) 将Runnable接口的子类对象作为实际参数传递给Thread类的构造器中。
 * 5) 调用Thread类的start方法：开启线程， 调用Runnable子类接口的run方法。
 */
public class MyRunnable implements Runnable{

	private String name;
	public MyRunnable(String name) {
		this.name=name;
	}
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("MyRunnable "+name+":"+i);
		}
	}
}
