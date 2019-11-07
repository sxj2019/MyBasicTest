package com.sxj.test.model;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * @program: test02
 * @description:
 * @author: sxj
 * @create: 2019-11-01 20:48
 **/
public class MyGC {

    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<>();
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(new WriterTask(deque),"T-"+i);
            t.start();
        }
        Thread c = new CleanerTask(deque);
        c.start();
    }
}

//事件
class Event{
    private Date createTime;
    private String info;

    public Event() {
    }

    public Event(Date createTime, String info) {
        this.createTime = createTime;
        this.info = info;
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

//
class WriterTask implements Runnable{

    private Deque<Event> deque;

    public WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event();
            event.setCreateTime(new Date());
            event.setInfo(String.format("%s create a Event!",Thread.currentThread().getName()));
            deque.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.printf("Writer: the size of deque: %d\n",deque.size());
        }
    }
}

class CleanerTask extends Thread{
    private Deque<Event> deque;

    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true){
            Date now = new Date();
            clean(now);
        }
    }

    public void clean(Date date) {
        long diff = 0;
//        System.out.printf("clean: size of deque: %d\n",deque.size());
        System.out.printf("222\n");
        if (deque.size() == 0){
            return;
        }
        System.out.printf("11\n");
        boolean isDel = false;
        do{
            System.out.printf("clean..");
            Event event = deque.getLast();
            System.out.println("time: "+event.getCreateTime());
            diff = date.getTime() - event.getCreateTime().getTime();
            System.out.printf("diff: %d\n",diff);
            if (diff > 2000){
                System.out.printf("Cleaner: %s\n", event.getInfo());
                deque.removeLast();
                isDel=true;
            }
        }while (diff > 2000);
        if (isDel){
            System.out.printf("Cleaner: size of Queue: %d\n",deque.size());
        }
    }
}