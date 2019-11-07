package com.sxj.test.dataStructure.Queue;

import com.sxj.test.dataStructure.Queue.CircleArrayQueue;
import com.sxj.test.dataStructure.Queue.MyQueue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Queue;

/**
 * @program: test02
 * @description:
 * @author: sxj
 * @create: 2019-11-07 08:48
 **/
public class TestQueue {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new CircleArrayQueue<>(3);
        try {
            queue.add(0);
            queue.add(1);
            queue.add(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(queue);
    }
}




