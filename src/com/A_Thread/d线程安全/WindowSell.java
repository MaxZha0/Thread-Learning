package com.A_Thread.d线程安全;

/**
 * @author Max
 * @date 2021/3/23 16:55
 */
public class WindowSell {
    public static void main(String[] args) {
        Window w1 = new Window("1");
        Window w2 = new Window("2");
        Window w3 = new Window("3");

        w1.start();
        w2.start();
        w3.start();
    }
}

class Window extends Thread{
    private String name;
    public Window(String name) {
        this.name=name;
    }
    private static int ticket = 100;
    private static Object obj = new Object();

    @Override
    public void run(){
        while (true){
            //必须要静态锁
            synchronized (obj){
                if(ticket > 0){
                    System.out.println(name+"窗口，票号："+ ticket);
                    ticket --;
                }else {
                    break;
                }
            }
        }
    }
}
