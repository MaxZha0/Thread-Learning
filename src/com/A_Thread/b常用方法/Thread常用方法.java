package com.A_Thread.b常用方法;

import static java.lang.Thread.sleep;

/**
 * 常用方法
 * start()
 * currentThread() 返回执行当前代码的线程
 * getName()
 * setName()
 * yield() 线程礼让，释放执行权
 * isAlive() 线程是否活跃
 * sleep(1000) 静态方法。线程休眠，阻塞ms，（会持有锁）
 * join() 线程插队运行，原线程阻塞，执行完毕以后才继续原来的线程
 * stop() 结束线程（已过时）
 *
 * wait() 使得调用如下方法的线程进入阻塞状态（会释放锁）
 * notify() 唤醒优先级高的线程
 * notifyAll() 唤醒所有线程
 *
 * @author max
 *
 */
public class Thread常用方法{

	public static void main(String[] args) {

		RunnableDemo02 r2=new RunnableDemo02("A");
		RunnableDemo03 r3=new RunnableDemo03("M");
		RunnableDemo03 r4=new RunnableDemo03("N");

		Thread t2=new Thread(r2);
		Thread t3=new Thread(r3);
		Thread t4=new Thread(r4);
		t2.start();
		System.out.println("线程是否活跃"+t2.isAlive());

		for (int i = 0; i < 50; i++) {
			if(i>20) {
				try {
					t2.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("主线程"+i);
		}
		t3.start();
		t4.start();
	}
}



class RunnableDemo02 implements Runnable{
	String name;
	public RunnableDemo02(String name) {
		this.name=name;
	}

	@Override
	public void run() {
		for (int i = 0; i<5; i++) {
			try {
				//每次运行休眠1000ms
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("当前线程名字"+Thread.currentThread().getName());
			System.out.println("线程休眠:"+i);

		}
	}
}

class RunnableDemo03 implements Runnable{
	String name;
	public RunnableDemo03(String name) {
		this.name=name;
	}
	@Override
	public void run() {
		for (int i = 0; i<20; i++) {
			System.out.println(name+":"+i);
			if(i==15) {
				System.out.println("线程礼让");
				//线程礼让
				Thread.yield();
			}
		}
	}
}


