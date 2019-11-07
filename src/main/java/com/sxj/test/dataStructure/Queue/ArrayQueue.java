package com.sxj.test.dataStructure.Queue;

/**
 * @program: test02
 * @description:
 * @author: sxj
 * @create: 2019-11-07 19:48
 **/
/**
 * 单向数组:
 *      1,tail = arr.len -1 时候，即数组已满
 *      2,head = tail 时，数组已空
 *      3,只能存放 arr.len-1 个数据
 */

public class ArrayQueue<E> implements MyQueue<E>{
    private Object[]  elements;
    private int head;
    private int tail;

    public ArrayQueue(int capacity) {
        elements = new Object[capacity];
        head = tail = 0;
    }

    @Override
    public void add(E e) throws Exception {
        if (tail == (elements.length -1)){
            throw new Exception("队列已满，head: "+head+",tail: "+tail);
        }
        elements[tail ++] = e;
    }

    @Override
    public E remove() throws Exception {
        if (head == tail){
            throw new Exception("队列为空，head: "+head+",tail: "+tail);
        }
        return (E) elements[head++];
    }

    @Override
    public int size() {
        return (tail-head);
    }

    @Override
    public String toString() {
        if (head == tail){
            return "Queue is empty";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("Queue:[");
        for (int i = head; i < tail; i++) {
            sb.append(elements[i]);
            if (i == (tail - 1)){
                sb.append("]");
            }else {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
