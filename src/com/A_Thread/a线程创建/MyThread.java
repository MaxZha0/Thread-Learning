package com.A_Thread.a线程创建;

/**
 * 1) 定义子类继承Thread类。
 * 2) 子类中重写Thread类中的run方法。
 * 3) 创建Thread子类对象，即创建了线程对象。
 * 4) 调用线程对象start方法：启动线程，调用run方法。
 */
public class MyThread extends Thread {

	private String name;
	public MyThread(String name) {
		this.name=name;
	}

	@Override
	public void run() {
		for (int i = 0; i <3; i++) {
			System.out.println("MyThread "+name+":"+i);
		}

	}
}
