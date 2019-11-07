package com.sxj.test.model;

/**
 * @program: test02
 * @description:
 * @author: sxj
 * @create: 2019-11-02 12:14
 **/
public class UnCatchExceptionTest {
    public static void main(String[] args) {
        Thread thread = new MyTask();
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}

class MyTask extends Thread{
    @Override
    public void run() {
        int x = Integer.parseInt("TT");
        System.out.printf("x");
    }
}

class ExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("An Exception has been caught!");
        System.out.printf("Thread: %s\n",t.getId());
        System.out.printf("Exception: %s: %s\n",e.getClass().getName(),e.getMessage());

    }
}
