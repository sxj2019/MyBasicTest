package com.sxj.test.dataStructure.Queue;

/**
 * @program: test02
 * @description: 循环数组:
 * @author: sxj
 * @create: 2019-11-07 19:49
 **/

public class CircleArrayQueue<E> implements MyQueue<E>{
    private Object[] elements;
    private int head;
    private int tail;
    private int capacity;

    public CircleArrayQueue(int capacity) {
        elements = new Object[capacity];
        head = tail = 0;
        this.capacity = capacity;
    }

    //在队尾添加数据
    @Override
    public void add(E e) throws Exception {
        if ((tail +1)%capacity == head){
            throw new Exception("Queue is Full! head: "+head+", tail: "+tail);
        }
        elements[tail] = e;
        tail = (tail + 1)%capacity;
    }

    //在对头删除数据
    @Override
    public E remove() throws Exception {
        if (head == tail)
            throw new Exception("Queue is Empty! head: "+head+",tail: "+tail);
        E e = (E) elements[head];
        head = (head + 1) % capacity;
        return e;
    }

    @Override
    public int size() {
        return ((tail - head) >= 0) ? (tail - head) : (tail - head);
    }

    @Override
    public String toString() {
        System.out.println("head: "+head+",tail: "+tail);
        if (head == tail){
            return "Queue is Empty!";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("Queue[");
        if (tail > head){
            for (int i = head; i <tail ; i++) {
                sb.append(elements[i]);
                if (i == (tail-1)){
                    sb.append("]");
                }else{
                    sb.append(",");
                }
            }
        }else if (tail < head){
            for (int i = head; i < elements.length; i++) {
                sb.append(elements[i]).append(",");
            }
            for (int j = 0; j < tail; j++) {
                sb.append(elements[j]);
                if (j == (tail -1)){
                    sb.append("]");
                }else {
                    sb.append(",");
                }
            }
        }

        return sb.toString();
    }
}
