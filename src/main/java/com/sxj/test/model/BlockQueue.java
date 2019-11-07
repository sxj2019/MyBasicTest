package com.sxj.test.model;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @program: test02
 * @description: 可阻塞的队列
 * @author: sxj
 * @create: 2019-11-01 19:07
 **/
public class BlockQueue<T> {

    private LinkedList<T> list = null;

    public BlockQueue() {
        list = new LinkedList<>();
    }

    public BlockQueue(LinkedList<T> list) {
        this.list = list;
    }

    public synchronized void push(T e) {
        list.add(e);
        this.notify();
    }

    public synchronized T pop() {
        while (list.size() == 0){
            try {
                System.out.println("no data...");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
        return list.remove(0);
    }

    @Override
    public String toString() {
        return Arrays.toString(list.toArray());
    }
}
