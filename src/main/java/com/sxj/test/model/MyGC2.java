package com.sxj.test.model;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @program: test02
 * @description:
 * @author: sxj
 * @create: 2019-11-02 11:29
 **/
public class MyGC2 {
    public static void main(String[] args) {
        Deque<Event2> deque = new ArrayDeque<>();
        for (int i = 0; i < 2; i++) {
            WriterThread t = new WriterThread(deque);
            t.start();
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

//        System.out.println(deque.toString());
//        Iterator<Event2> iterator = deque.iterator();
//        while (iterator.hasNext()){
//            Event2 next = iterator.next();
//            System.out.println(next.getCreateTime()+", "+next.getInfo());
//        }
        CleanerThread cleaner = new CleanerThread(deque);
        cleaner.start();
        System.out.println("main is over...");

    }
}

//事件
class Event2{
    private Date createTime;
    private String info;

    public Event2() {
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
//创建事件的线程
class WriterThread extends Thread{
    private Deque<Event2> deque;

    public WriterThread(Deque<Event2> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Event2 event = new Event2();
            event.setCreateTime(new Date());
            event.setInfo(String.format("%s create event",Thread.currentThread().getName()));
            deque.addFirst(event);
//            System.out.println(Thread.currentThread().getName()+" create!");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//清理事件线程
class CleanerThread extends Thread{
    private Deque<Event2> deque;

    public CleanerThread(Deque<Event2> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        while (true){
            clean();
        }
    }

    public void clean() {
        Date now = new Date();
        long diff = 0L;
        boolean isDel = false;
        if (deque.size() == 0){
            return;
        }
        do {
            Event2 last = deque.getLast();
            diff = now.getTime() - last.getCreateTime().getTime();
            if (diff > 1000L){
                System.out.println("Clean:  "+last.getInfo());
                deque.remove(last);
                isDel = true;
            }
        }while (diff > 1000);

        if (isDel){
            System.out.printf("Cleaner: size of dequeue: %d\n",deque.size());
        }
    }
}
