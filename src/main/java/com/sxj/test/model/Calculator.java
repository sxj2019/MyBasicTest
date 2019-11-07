package com.sxj.test.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: test02
 * @description:
 * @author: sxj
 * @create: 2019-11-01 10:15
 **/
public class Calculator implements Runnable {
    private int num;

    public Calculator(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 1; i < 10; i++) {
            System.out.printf("%s: %d * %d = %d\n",Thread.currentThread().getName(),num,i,i * num);
        }
    }

    public static void main(String[] args) {
//        Thread t1 = new Thread(new Calculator(8));
//        t1.start();
        Thread[] threads = new Thread[10];
        Thread.State[] states = new Thread.State[10];
        for (int i = 1; i < 10; i++) {
            threads[i] = new Thread(new Calculator(i));
            if (i % 2 == 0){
                threads[i].setPriority(Thread.MAX_PRIORITY);
            }else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread-"+i);
        }
        try (FileWriter file = new FileWriter("log.txt");
             PrintWriter pw =new PrintWriter(file);
        ){
            //把线程状态写入文件
            for (int i = 1; i < 10; i++) {
                pw.println("Main : Status of Thread "+i+" : " +threads[i].getState());
                states[i] = threads[i].getState();
            }
            //执行线程
            for (int i = 1; i < 10; i++) {
                threads[i].start();
            }

            //检查
            boolean finish = false;
            while (!finish){
                for (int i = 1; i < 10; i++) {
                    if (threads[i].getState() != states[i]){//状态有改变
                        //将状态写入文件
                        writeStateToFile(pw,threads[i],states[i]);
                        states[i] = threads[i].getState();
                    }
                    finish = true;
                    for (int j = 1; j < 10; j++) {
                        finish = finish && (threads[i].getState()== Thread.State.TERMINATED);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeStateToFile(PrintWriter pw,Thread thread,Thread.State state) {
        pw.printf("Main: Id %d- %s\n",thread.getId(),thread.getName());
        pw.printf("Main: Priority: %d\n",thread.getPriority());
        pw.printf("Main: oldStats: %s\n",state);
        pw.printf("Main: newState: %s\n",thread.getState());
        pw.printf("Main:*************************");
    }
}
